package pk.engineeringthesis.laboratoriesmanagementsystem.searchengine;

import org.springframework.stereotype.Repository;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.Laboratory;

import java.util.List;

@Repository
public interface SearchEngineRepository {

    List<Laboratory> searchLaboratoryByName(final String keywords);
    List<Laboratory> searchLaboratoryByDescription(final String keywords);
    List<Laboratory> searchLaboratoryByIntended(final String keywords);
    List<Laboratory> searchLaboratoryByEquipmentLocation(final String keywords);
    List<Laboratory> searchLaboratoryByEquipmentName(final String keywords);
    List<Laboratory> searchLaboratoryByEquipmentType(final String keywords);
    List<Laboratory> searchLaboratoryByEquipmentDescription(final String keywords);
}
