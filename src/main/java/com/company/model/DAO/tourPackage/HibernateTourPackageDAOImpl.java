package com.company.model.DAO.tourPackage;

import com.company.controller.utils.ParametersSelectedForTourPackages;
import com.company.model.DAO.utils.HibernateSessionUtil;
import com.company.model.domain.tourPackage.TourPackage;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
    private HibernateSessionUtil hibernateSessionUtil;

    @Autowired
    public HibernateTourPackageDAOImpl(HibernateSessionUtil hibernateSessionUtil) {
        this.hibernateSessionUtil = hibernateSessionUtil;
    }

    public HibernateTourPackageDAOImpl() {
    }


    @Override
    public List<TourPackage> getAllTourPackages() {
        TypedQuery<TourPackage> query = hibernateSessionUtil.getSession().createQuery("from TourPackage ").setCacheable(true);
        return query.getResultList();

    }

    @Override
    public TourPackage getTourPackageById(Long id) {
        TourPackage tourPackage =  hibernateSessionUtil.getSession().get(TourPackage.class, id);
        return tourPackage;
    }

    @Override
    public List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages) {
        CriteriaBuilder builder = hibernateSessionUtil.getSession().getCriteriaBuilder();
        CriteriaQuery<TourPackage> query = builder.createQuery(TourPackage.class);
        Root<TourPackage> root = query.from(TourPackage.class);
        List<Predicate> predicatesList = createPredicatesFromParametersSelectedForTourPackages(builder, parametersSelectedForTourPackages, root);
        Predicate[] predicates = predicatesList.toArray(new Predicate[predicatesList.size()]);
        query.select(root).where(predicates);
        Query<TourPackage> q= hibernateSessionUtil.getSession().createQuery(query);
        List<TourPackage> resultList=q.getResultList();
        return resultList;
    }

    @Override
    public Long createTourPackage(TourPackage tourPackage) {
        return (Long) hibernateSessionUtil.getSession().save(tourPackage);
    }

    @Override
    public void updateTourPackage(TourPackage tourPackage) {
        hibernateSessionUtil.getSession().saveOrUpdate(tourPackage);
    }

    @Override
    public void deleteTourPackage(TourPackage tourPackage) {
        hibernateSessionUtil.getSession().delete(tourPackage);
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
