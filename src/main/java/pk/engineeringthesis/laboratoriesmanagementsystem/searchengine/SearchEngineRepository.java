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

    List<Laboratory> searchLaboratoryByNameWithSupervisor(final String keywords,final String supervisor);
    List<Laboratory> searchLaboratoryByDescriptionWithSupervisor(final String keywords,final String supervisor);
    List<Laboratory> searchLaboratoryByIntendedWithSupervisor(final String keywords,final String supervisor);
    List<Laboratory> searchLaboratoryByEquipmentLocationWithSupervisor(final String keywords,final String supervisor);
    List<Laboratory> searchLaboratoryByEquipmentNameWithSupervisor(final String keywords,final String supervisor);
    List<Laboratory> searchLaboratoryByEquipmentTypeWithSupervisor(final String keywords,final String supervisor);
    List<Laboratory> searchLaboratoryByEquipmentDescriptionWithSupervisor(final String keywords,final String supervisor);

}
