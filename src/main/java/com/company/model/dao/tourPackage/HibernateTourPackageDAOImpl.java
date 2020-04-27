package com.company.model.dao.tourPackage;

import com.company.utils.ParametersSelectedForTourPackages;
import com.company.model.dao.GenericHibernateDAO;
import com.company.model.domain.tourPackage.TourPackage;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class HibernateTourPackageDAOImpl extends GenericHibernateDAO<TourPackage, Long> implements TourPackageDAO {

    @Autowired
    public HibernateTourPackageDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<TourPackage> findAll() {
        Query<TourPackage> query =getSession().createQuery("from TourPackage T order by T.type.name, T.price").setCacheable(true);
        return query.getResultList();
    }

    @Override
    public List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<TourPackage> query = builder.createQuery(TourPackage.class);
        Root<TourPackage> root = query.from(TourPackage.class);
        query.orderBy(builder.asc(root.get("type").get("name")),builder.asc(root.get("price")));
        List<Predicate> predicatesList = createPredicatesFromParametersSelectedForTourPackages(builder, parametersSelectedForTourPackages, root);
        Predicate[] predicates = predicatesList.toArray(new Predicate[predicatesList.size()]);
        query.select(root).where(predicates);
        Query<TourPackage> q= getSession().createQuery(query);
        List<TourPackage> resultList=q.getResultList();
        return resultList;
    }

    @Override
    public void deleteById(Long id) {
        Query<TourPackage> query =getSession().createQuery("delete TourPackage where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    private List<Predicate> createPredicatesFromParametersSelectedForTourPackages(CriteriaBuilder builder
            , ParametersSelectedForTourPackages parametersSelectedForTourPackages
            , Root<TourPackage> root){
        List<Predicate> predicates = new ArrayList<>();

        if(!parametersSelectedForTourPackages.getValueOfType().isEmpty())
            predicates.add(builder.equal(root.get("type").get("name"), parametersSelectedForTourPackages.getValueOfType()));

        if(!parametersSelectedForTourPackages.getValueOfFoodSystem().isEmpty())
            predicates.add(builder.equal(root.get("foodSystem").get("name"), parametersSelectedForTourPackages.getValueOfFoodSystem()));

        if(!parametersSelectedForTourPackages.getValueOfTransport().isEmpty())
            predicates.add(builder.equal(root.get("transport").get("name"), parametersSelectedForTourPackages.getValueOfTransport()));

        if(!(parametersSelectedForTourPackages.getMinDay().isEmpty()||parametersSelectedForTourPackages.getMaxDay().isEmpty()))
        predicates.add(builder.between(root.get("days")
                , Integer.parseInt(parametersSelectedForTourPackages.getMinDay())
                , Integer.parseInt(parametersSelectedForTourPackages.getMaxDay())
        ));

        if(!(parametersSelectedForTourPackages.getMinPrice().isEmpty()||parametersSelectedForTourPackages.getMaxPrice().isEmpty()))
        predicates.add(builder.between(root.get("price")
                , Integer.parseInt(parametersSelectedForTourPackages.getMinPrice())
                , Integer.parseInt(parametersSelectedForTourPackages.getMaxPrice())
        ));

        if(parametersSelectedForTourPackages.isStatusHot())
            predicates.add(builder.equal(root.get("statusHot"), parametersSelectedForTourPackages.isStatusHot()));

        return predicates;
    }

}
