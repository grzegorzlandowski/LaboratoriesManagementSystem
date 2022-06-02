package pk.engineeringthesis.laboratoriesmanagementsystem.searchengine;

import java.util.List;

import javax.persistence.EntityManager;


import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import lombok.RequiredArgsConstructor;

import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.Laboratory;



@Service
@RequiredArgsConstructor
public class SearchEngineService implements SearchEngineRepository {

    private final EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Laboratory> searchLaboratoryByName(final String keywords) {

        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);


        final QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
                .forEntity(Laboratory.class).get();


        final BooleanJunction<BooleanJunction> outer = queryBuilder.bool();
        outer.must(queryBuilder.keyword().onFields("name").matching(keywords).createQuery());
               List<Laboratory> resultList = fullTextEntityManager.createFullTextQuery(outer.createQuery(), Laboratory.class)
                .getResultList();
        return resultList;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Laboratory> searchLaboratoryByDescription(final String keywords) {

        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);


        final QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
                .forEntity(Laboratory.class).get();


        final BooleanJunction<BooleanJunction> outer = queryBuilder.bool();
        outer.must(queryBuilder.keyword().onFields("description").matching(keywords).createQuery());
        List<Laboratory> resultList = fullTextEntityManager.createFullTextQuery(outer.createQuery(), Laboratory.class)
                .getResultList();
        return resultList;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Laboratory> searchLaboratoryByIntended(final String keywords) {

        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);


        final QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
                .forEntity(Laboratory.class).get();


        final BooleanJunction<BooleanJunction> outer = queryBuilder.bool();
        outer.must(queryBuilder.keyword().onFields("intended").matching(keywords).createQuery());
        List<Laboratory> resultList = fullTextEntityManager.createFullTextQuery(outer.createQuery(), Laboratory.class)
                .getResultList();
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Laboratory> searchLaboratoryByEquipmentLocation(final String keywords) {

        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);


        final QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
                .forEntity(Laboratory.class).get();


        final BooleanJunction<BooleanJunction> outer = queryBuilder.bool();
        outer.must(queryBuilder.keyword().onFields("laboratoryEquipment.location").matching(keywords).createQuery());
        List<Laboratory> resultList = fullTextEntityManager.createFullTextQuery(outer.createQuery(), Laboratory.class)
                .getResultList();
        return resultList;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Laboratory> searchLaboratoryByEquipmentName(final String keywords) {

        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);


        final QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
                .forEntity(Laboratory.class).get();


        final BooleanJunction<BooleanJunction> outer = queryBuilder.bool();
        outer.must(queryBuilder.keyword().onFields("laboratoryEquipment.equipmentDetails.title").matching(keywords).createQuery());
        List<Laboratory> resultList = fullTextEntityManager.createFullTextQuery(outer.createQuery(), Laboratory.class)
                .getResultList();
        return resultList;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Laboratory> searchLaboratoryByEquipmentType(final String keywords) {

        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);


        final QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
                .forEntity(Laboratory.class).get();


        final BooleanJunction<BooleanJunction> outer = queryBuilder.bool();
        outer.must(queryBuilder.keyword().onFields("laboratoryEquipment.equipmentDetails.type").matching(keywords).createQuery());
        List<Laboratory> resultList = fullTextEntityManager.createFullTextQuery(outer.createQuery(), Laboratory.class)
                .getResultList();
        return resultList;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Laboratory> searchLaboratoryByEquipmentDescription(final String keywords) {

        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);


        final QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
                .forEntity(Laboratory.class).get();


        final BooleanJunction<BooleanJunction> outer = queryBuilder.bool();
        outer.must(queryBuilder.keyword().onFields("laboratoryEquipment.equipmentDetails.description").matching(keywords).createQuery());
        //outer.must(queryBuilder.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(0).onField("laboratoryEquipment.equipmentDetails.description").matching(keywords).createQuery());
        //outer.must(queryBuilder.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(0).onField("tytul").matching(keywords).createQuery())
        //.must(queryBuilder.keyword().onField("dostepnosc").matching("false").createQuery())
        //.createQuery();

        List<Laboratory> resultList = fullTextEntityManager.createFullTextQuery(outer.createQuery(), Laboratory.class)
                .getResultList();
        return resultList;
    }
}
