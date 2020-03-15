package com.company.model.DAO;

import com.company.controller.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.TourPackage.TourPackage;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

@Repository
//@Transactional
public class HibernateTourPackageDAOImpl implements TourPackageDAO {
    private SessionFactory sessionFactory;

    static private List<TourPackage> listAll=new ArrayList<>();
    static private List<TourPackage> listSelected=new ArrayList<>();
    static {
        TourPackage tourPackage=new TourPackage();
        TourPackage tourPackage1=new TourPackage();
        TourPackage tourPackage2=new TourPackage();
        TourPackage tourPackage3=new TourPackage();
        tourPackage.setName("tour");
        tourPackage.setFoodSystem("ai");
        tourPackage.setTransport("bus");
        tourPackage1.setName("tour1");
        tourPackage1.setFoodSystem("ai1");
        tourPackage1.setTransport("bus1");
        tourPackage2.setName("tour2");
        tourPackage2.setFoodSystem("ai2");
        tourPackage2.setTransport("bus2");
        tourPackage3.setName("tour3");
        tourPackage3.setFoodSystem("ai3");
        tourPackage3.setTransport("bus3");
        listAll.add(tourPackage);
        listAll.add(tourPackage1);
        listAll.add(tourPackage2);
        listAll.add(tourPackage3);

        listSelected.add(tourPackage2);
        listSelected.add(tourPackage3);
    }

   // @Autowired
    //public HibernateTourPackageDAOImpl(SessionFactory sessionFactory) {
      //  this.sessionFactory = sessionFactory;
   // }


    public HibernateTourPackageDAOImpl() {
    }

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<TourPackage> getAllTourPackages() {
        //TypedQuery<TourPackage> query = sessionFactory.getCurrentSession().createQuery("from TourPackage ");
        //return query.getResultList();
        return listAll;
    }

    @Override
    public TourPackage getTourPackageById(Long id) {
       // TypedQuery<TourPackage> query = sessionFactory.getCurrentSession().createQuery("select T from TourPackage T where T.id="+id);
        //return query.getSingleResult();
        return null;
    }

    @Override
    public List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages) {
        return listSelected;
    }
}
