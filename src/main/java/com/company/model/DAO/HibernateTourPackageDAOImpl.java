package com.company.model.DAO;

import com.company.controller.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.TourPackage.FoodSystem;
import com.company.model.domain.TourPackage.TourPackage;
import com.company.model.domain.TourPackage.TourPackageType;
import com.company.model.domain.TourPackage.Transport;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class HibernateTourPackageDAOImpl implements TourPackageDAO {
    private SessionFactory sessionFactory;

    @Autowired
   public HibernateTourPackageDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public HibernateTourPackageDAOImpl() {
    }

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<TourPackage> getAllTourPackages() {
        TypedQuery<TourPackage> query = sessionFactory.getCurrentSession().createQuery("from TourPackage ").setCacheable(true);
        return query.getResultList();

    }

    @Override
    public TourPackage getTourPackageById(Long id) {
        Session session=sessionFactory.getCurrentSession();
        TourPackage tourPackage =  session.load(TourPackage.class, 1L);
        return tourPackage;
    }

    @Override
    public List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages) {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<TourPackage> query = builder.createQuery(TourPackage.class);
        Root<TourPackage> root = query.from(TourPackage.class);
        List<Predicate> predicatesList = createPredicatesFromParametersSelectedForTourPackages(builder, parametersSelectedForTourPackages, root);
        Predicate[] predicates = predicatesList.toArray(new Predicate[predicatesList.size()]);
        query.select(root).where(predicates);
        Query<TourPackage> q=sessionFactory.getCurrentSession().createQuery(query);
        List<TourPackage> resultList=q.getResultList();
        return resultList;
    }

    private List<Predicate> createPredicatesFromParametersSelectedForTourPackages(CriteriaBuilder builder
            , ParametersSelectedForTourPackages parametersSelectedForTourPackages
            , Root<TourPackage> root){
        List<Predicate> predicates = new ArrayList<>();

        if(!parametersSelectedForTourPackages.getValueOfType().isEmpty())
            predicates.add(builder.equal(root.get("type"), TourPackageType.valueOf(parametersSelectedForTourPackages.getValueOfType())));

        if(!parametersSelectedForTourPackages.getValueOfFoodSystem().isEmpty())
            predicates.add(builder.equal(root.get("foodSystem"), FoodSystem.valueOf(parametersSelectedForTourPackages.getValueOfFoodSystem())));

        if(!parametersSelectedForTourPackages.getValueOfTransport().isEmpty())
            predicates.add(builder.equal(root.get("transport"), Transport.valueOf(parametersSelectedForTourPackages.getValueOfTransport())));

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
