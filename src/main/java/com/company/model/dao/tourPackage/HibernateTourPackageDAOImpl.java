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

/**
 * This class is concrete repository, that provides implementation of operations for working with tour package's entities.
 * It uses hibernate ORM for working with data base.
 *
 * @author Igor Ivanov
 */
@Repository
@Transactional
public class HibernateTourPackageDAOImpl extends GenericHibernateDAO<TourPackage, Long> implements TourPackageDAO
{
    /**
     * Constructor which receive hibernate's session factory and pass it to super class constructor.
     *
     * @param sessionFactory - hibernate's session factory.
     */
    @Autowired
    public HibernateTourPackageDAOImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }

    /**
     * This method return all stored in repository tour packages.
     *
     * @return list of stored entities.
     */
    @Override
    public List<TourPackage> findAll()
    {
        Query<TourPackage> query = getSession().createQuery("from TourPackage T order by T.type.name, T.price")
                .setCacheable(true);
        return query.getResultList();
    }

    /**
     * This method receive parameters for selecting tour packages and return selected tour packages.
     *
     * @param parametersSelectedForTourPackages - parameters for selecting tour packages.
     * @return selected tour packages.
     */
    @Override
    public List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages)
    {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<TourPackage> query = builder.createQuery(TourPackage.class);
        Root<TourPackage> root = query.from(TourPackage.class);
        query.orderBy(builder.asc(root.get("type").get("name")), builder.asc(root.get("price")));
        List<Predicate> predicatesList = createPredicatesFromParametersSelectedForTourPackages(builder, parametersSelectedForTourPackages, root);
        Predicate[] predicates = predicatesList.toArray(new Predicate[predicatesList.size()]);
        query.select(root).where(predicates);
        Query<TourPackage> q = getSession().createQuery(query);
        List<TourPackage> resultList = q.getResultList();
        return resultList;
    }

    /**
     * This method delete tour package by identifier.
     *
     * @param id - tour package's identifier.
     */
    @Override
    public void deleteById(Long id)
    {
        Query<TourPackage> query = getSession().createQuery("delete TourPackage where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    /**
     * This private method create predicates from selected parameters.
     *
     * @param builder                           - hibernate's criteria builder.
     * @param parametersSelectedForTourPackages - parameters for selecting tour packages.
     * @param root                              - query root.
     * @return predicates from selected parameters.
     */
    private List<Predicate> createPredicatesFromParametersSelectedForTourPackages(CriteriaBuilder builder
            , ParametersSelectedForTourPackages parametersSelectedForTourPackages
            , Root<TourPackage> root)
    {
        List<Predicate> predicates = new ArrayList<>();

        if (parametersSelectedForTourPackages.getIdOfType() != null)
        {
            predicates.add(builder.equal(root.get("type").get("id"), parametersSelectedForTourPackages.getIdOfType()));
        }

        if (parametersSelectedForTourPackages.getIdOfFoodSystem() != null)
        {
            predicates.add(builder.equal(root.get("foodSystem").get("id"), parametersSelectedForTourPackages.getIdOfFoodSystem()));
        }

        if (parametersSelectedForTourPackages.getIdOfTransport() != null)
           {
               predicates.add(builder.equal(root.get("transport").get("id"), parametersSelectedForTourPackages.getIdOfTransport()));
           }

        if (!(parametersSelectedForTourPackages.getMinDay().isEmpty() || parametersSelectedForTourPackages.getMaxDay().isEmpty()))
        {
            predicates.add(builder.between(root.get("days")
                    , Integer.parseInt(parametersSelectedForTourPackages.getMinDay())
                    , Integer.parseInt(parametersSelectedForTourPackages.getMaxDay())
            ));
        }
        else
        {
            if (!parametersSelectedForTourPackages.getMinDay().isEmpty())
            {
                predicates.add(builder.gt(root.get("days")
                        , Integer.parseInt(parametersSelectedForTourPackages.getMinDay())
                ));
            }
            if (!parametersSelectedForTourPackages.getMaxDay().isEmpty())
            {
                predicates.add(builder.lt(root.get("days")
                        , Integer.parseInt(parametersSelectedForTourPackages.getMaxDay())
                ));
            }
        }

        if (!(parametersSelectedForTourPackages.getMinPrice().isEmpty() || parametersSelectedForTourPackages.getMaxPrice().isEmpty()))
        {
            predicates.add(builder.between(root.get("price")
                    , Integer.parseInt(parametersSelectedForTourPackages.getMinPrice())
                    , Integer.parseInt(parametersSelectedForTourPackages.getMaxPrice())
            ));
        }
        else
        {
            if (!parametersSelectedForTourPackages.getMinPrice().isEmpty())
            {
                predicates.add(builder.gt(root.get("price")
                        , Integer.parseInt(parametersSelectedForTourPackages.getMinPrice())
                ));
            }
            if (!parametersSelectedForTourPackages.getMaxPrice().isEmpty())
            {
                predicates.add(builder.lt(root.get("price")
                        , Integer.parseInt(parametersSelectedForTourPackages.getMaxPrice())
                ));
            }
        }

        if (parametersSelectedForTourPackages.isStatusHot())
        {
            predicates.add(builder.equal(root.get("statusHot"), parametersSelectedForTourPackages.isStatusHot()));
        }

        return predicates;
    }
}
