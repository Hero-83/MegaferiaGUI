package Manage;

import core.*;
import java.util.ArrayList;

/**
 * Clase responsable de registrar entidades con todas las validaciones
 * @author hero83
 */
public class RegistrationManager {
    
    /**
     * Registra un nuevo Stand con validaciones completas
     */
    public static String registerStand(String idStr, String priceStr, ArrayList<Stand> stands) {
        // Validar formato de ID
        if (!FormatValidator.isNotEmpty(idStr)) {
            return "Error: ID inválido (debe ser ≥0 y ≤15 dígitos)";
        }
        if (!FormatValidator.isValidIdFormat(idStr)) {
            return "Error: ID inválido (debe ser ≥0 y ≤15 dígitos)";
        }
        if (!FormatValidator.isNotEmpty(priceStr)) {
            return "Error: Precio inválido (debe ser >0)";
        }
        // Validar formato de precio
        if (!FormatValidator.isValidPrice(priceStr)) {
            return "Error: Precio inválido (debe ser >0)";
        }
        
        long id = Long.parseLong(idStr.trim());
        double price = Double.parseDouble(priceStr.trim());
        
        // Validar unicidad de ID
        if (!UniqueValidator.isUniqueStandId(id, stands)) {
            return "Error: ID de stand ya existe";
        }
        
        // Crear y agregar stand
        Stand newStand = new Stand(id, price);
        stands.add(newStand);
        
        return "Stand registrado exitosamente";
    }
    
    /**
     * Registra un nuevo Autor con validaciones completas
     */
    public static String registerAuthor(String idStr, String firstname, String lastname, ArrayList<Author> authors) {
        // Validar formato de ID
        if (!FormatValidator.isValidIdFormat(idStr)) {
            return "Error: ID inválido (debe ser ≥0 y ≤15 dígitos)";
        }
        
        // Validar campos no vacíos
        if (!FormatValidator.isNotEmpty(firstname)) {
            return "Error: Nombre no puede estar vacío";
        }
        if (!FormatValidator.isNotEmpty(lastname)) {
            return "Error: Apellido no puede estar vacío";
        }
        
        long id = Long.parseLong(idStr.trim());
        
        // Validar unicidad de ID
        if (!UniqueValidator.isUniquePersonId(id, authors)) {
            return "Error: ID de persona ya existe";
        }
        
        // Crear y agregar autor
        Author newAuthor = new Author(id, firstname.trim(), lastname.trim());
        authors.add(newAuthor);
        
        return "Autor registrado exitosamente";
    }
    
    /**
     * Registra un nuevo Gerente con validaciones completas
     */
    public static String registerManager(String idStr, String firstname, String lastname, ArrayList<Manager> managers) {
        // Validar formato de ID
        if (!FormatValidator.isValidIdFormat(idStr)) {
            return "Error: ID inválido (debe ser ≥0 y ≤15 dígitos)";
        }
        
        // Validar campos no vacíos
        if (!FormatValidator.isNotEmpty(firstname)) {
            return "Error: Nombre no puede estar vacío";
        }
        if (!FormatValidator.isNotEmpty(lastname)) {
            return "Error: Apellido no puede estar vacío";
        }
        
        long id = Long.parseLong(idStr.trim());
        
        // Validar unicidad de ID
        if (!UniqueValidator.isUniquePersonId(id, managers)) {
            return "Error: ID de persona ya existe";
        }
        
        // Crear y agregar gerente
        Manager newManager = new Manager(id, firstname.trim(), lastname.trim());
        managers.add(newManager);
        
        return "Gerente registrado exitosamente";
    }
    
    /**
     * Registra un nuevo Narrador con validaciones completas
     */
    public static String registerNarrator(String idStr, String firstname, String lastname, ArrayList<Narrator> narrators) {
        // Validar formato de ID
        if (!FormatValidator.isValidIdFormat(idStr)) {
            return "Error: ID inválido (debe ser ≥0 y ≤15 dígitos)";
        }
        
        // Validar campos no vacíos
        if (!FormatValidator.isNotEmpty(firstname)) {
            return "Error: Nombre no puede estar vacío";
        }
        if (!FormatValidator.isNotEmpty(lastname)) {
            return "Error: Apellido no puede estar vacío";
        }
        
        long id = Long.parseLong(idStr.trim());
        
        // Validar unicidad de ID
        if (!UniqueValidator.isUniquePersonId(id, narrators)) {
            return "Error: ID de persona ya existe";
        }
        
        // Crear y agregar narrador
        Narrator newNarrator = new Narrator(id, firstname.trim(), lastname.trim());
        narrators.add(newNarrator);
        
        return "Narrador registrado exitosamente";
    }
    
    /**
     * Registra una nueva Editorial con validaciones completas
     */
    public static String registerPublisher(String nit, String name, String address, long managerId, 
                                         ArrayList<Publisher> publishers, ArrayList<Manager> managers) {
        // Validar formato de NIT
        if (!FormatValidator.isValidNIT(nit)) {
            return "Error: NIT inválido (formato: XXX.XXX.XXX-X)";
        }
        
        // Validar campos no vacíos
        if (!FormatValidator.isNotEmpty(name)) {
            return "Error: Nombre no puede estar vacío";
        }
        if (!FormatValidator.isNotEmpty(address)) {
            return "Error: Dirección no puede estar vacía";
        }
        
        // Validar unicidad de NIT
        if (!UniqueValidator.isUniqueNIT(nit, publishers)) {
            return "Error: NIT ya existe";
        }
        
        // Validar que el gerente existe
        if (!ExistenceValidator.managerExists(managerId, managers)) {
            return "Error: Gerente no existe";
        }
        
        // Buscar el gerente
        Manager manager = null;
        for (Manager m : managers) {
            if (m.getId() == managerId) {
                manager = m;
                break;
            }
        }
        
        // Crear y agregar editorial
        Publisher newPublisher = new Publisher(nit.trim(), name.trim(), address.trim(), manager);
        publishers.add(newPublisher);
        
        return "Editorial registrada exitosamente";
    }
}