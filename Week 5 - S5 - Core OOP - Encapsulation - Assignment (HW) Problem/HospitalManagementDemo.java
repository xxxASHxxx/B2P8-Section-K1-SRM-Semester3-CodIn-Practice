import java.time.LocalDate;
import java.util.Arrays;

// Final class to prevent inheritance
public final class MedicalRecord {
    // All fields are private and final for immutability
    private final String recordId;
    private final String patientDNA;
    private final String[] allergies;
    private final String[] medicalHistory;
    private final LocalDate birthDate;
    private final String bloodType;
    
    // HIPAA compliance validation constants
    private static final String DNA_PATTERN = "^[ATCG]+$";
    private static final String[] VALID_BLOOD_TYPES = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
    
    // Constructor with HIPAA compliance validation
    public MedicalRecord(String recordId, String patientDNA, String[] allergies, 
                        String[] medicalHistory, LocalDate birthDate, String bloodType) {
        // Validate required fields
        if (recordId == null || recordId.trim().isEmpty()) {
            throw new IllegalArgumentException("Record ID cannot be null or empty");
        }
        if (patientDNA == null || !patientDNA.matches(DNA_PATTERN)) {
            throw new IllegalArgumentException("Invalid DNA sequence");
        }
        if (birthDate == null || birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid birth date");
        }
        if (!Arrays.asList(VALID_BLOOD_TYPES).contains(bloodType)) {
            throw new IllegalArgumentException("Invalid blood type");
        }
        
        this.recordId = recordId;
        this.patientDNA = patientDNA;
        // Defensive copying for mutable arrays
        this.allergies = allergies != null ? allergies.clone() : new String[0];
        this.medicalHistory = medicalHistory != null ? medicalHistory.clone() : new String[0];
        this.birthDate = birthDate;
        this.bloodType = bloodType;
    }
    
    // Getters with defensive copying
    public String getRecordId() {
        return recordId;
    }
    
    public String getPatientDNA() {
        return patientDNA;
    }
    
    public String[] getAllergies() {
        return allergies.clone(); // Defensive copy
    }
    
    public String[] getMedicalHistory() {
        return medicalHistory.clone(); // Defensive copy
    }
    
    public LocalDate getBirthDate() {
        return birthDate; // LocalDate is immutable
    }
    
    public String getBloodType() {
        return bloodType;
    }
    
    // Final method for safety - cannot be overridden
    public final boolean isAllergicTo(String substance) {
        if (substance == null) return false;
        return Arrays.stream(allergies)
                    .anyMatch(allergy -> allergy.equalsIgnoreCase(substance));
    }
    
    @Override
    public String toString() {
        return "MedicalRecord{" +
               "recordId='" + recordId + '\'' +
               ", birthDate=" + birthDate +
               ", bloodType='" + bloodType + '\'' +
               ", allergiesCount=" + allergies.length +
               ", historyCount=" + medicalHistory.length +
               '}';
    }
}
public class Patient {
    // Protected health information - private final
    private final String patientId;
    private final MedicalRecord medicalRecord;
    
    // Modifiable personal data - private
    private String currentName;
    private String emergencyContact;
    private String insuranceInfo;
    
    // Current treatment info - private
    private int roomNumber;
    private String attendingPhysician;
    
    // Emergency admission constructor (minimal data)
    public Patient(String currentName) {
        this.patientId = "TEMP_" + System.currentTimeMillis();
        this.currentName = currentName;
        this.medicalRecord = null; // Will be updated later
        this.roomNumber = 0;
        this.emergencyContact = "UNKNOWN";
        this.insuranceInfo = "PENDING";
        this.attendingPhysician = "EMERGENCY_STAFF";
    }
    
    // Standard admission constructor (full information)
    public Patient(String patientId, String currentName, MedicalRecord medicalRecord,
                   String emergencyContact, String insuranceInfo, int roomNumber,
                   String attendingPhysician) {
        validatePrivacyPermissions(patientId, medicalRecord);
        
        this.patientId = patientId;
        this.currentName = currentName;
        this.medicalRecord = medicalRecord;
        this.emergencyContact = emergencyContact;
        this.insuranceInfo = insuranceInfo;
        this.roomNumber = roomNumber;
        this.attendingPhysician = attendingPhysician;
    }
    
    // Transfer admission constructor (imports existing medical record)
    public Patient(String patientId, String currentName, MedicalRecord existingRecord,
                   int newRoomNumber, String newPhysician) {
        validatePrivacyPermissions(patientId, existingRecord);
        
        this.patientId = patientId;
        this.currentName = currentName;
        this.medicalRecord = existingRecord;
        this.roomNumber = newRoomNumber;
        this.attendingPhysician = newPhysician;
        this.emergencyContact = "TRANSFERRED";
        this.insuranceInfo = "EXISTING";
    }
    
    // Privacy validation for constructors
    private void validatePrivacyPermissions(String patientId, MedicalRecord record) {
        if (patientId == null || patientId.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient ID required for privacy compliance");
        }
        if (record == null) {
            throw new IllegalArgumentException("Medical record required for data integrity");
        }
    }
    
    // Package-private method for hospital staff access
    String getBasicInfo() {
        return "Patient{" +
               "patientId='" + patientId + '\'' +
               ", name='" + currentName + '\'' +
               ", room=" + roomNumber +
               ", physician='" + attendingPhysician + '\'' +
               ", emergency='" + emergencyContact + '\'' +
               '}';
    }
    
    // Public method - only non-sensitive data
    public String getPublicInfo() {
        return "Name: " + currentName + ", Room: " + roomNumber;
    }
    
    // JavaBean-compliant getters and setters
    public String getPatientId() {
        return patientId;
    }
    
    public MedicalRecord getMedicalRecord() {
        return medicalRecord; // Immutable, safe to return
    }
    
    public String getCurrentName() {
        return currentName;
    }
    
    public void setCurrentName(String currentName) {
        if (currentName != null && !currentName.trim().isEmpty()) {
            this.currentName = currentName;
        }
    }
    
    public String getEmergencyContact() {
        return emergencyContact;
    }
    
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
    
    public String getInsuranceInfo() {
        return insuranceInfo;
    }
    
    public void setInsuranceInfo(String insuranceInfo) {
        this.insuranceInfo = insuranceInfo;
    }
    
    public int getRoomNumber() {
        return roomNumber;
    }
    
    public void setRoomNumber(int roomNumber) {
        if (roomNumber > 0) {
            this.roomNumber = roomNumber;
        }
    }
    
    public String getAttendingPhysician() {
        return attendingPhysician;
    }
    
    public void setAttendingPhysician(String attendingPhysician) {
        this.attendingPhysician = attendingPhysician;
    }
    
    @Override
    public String toString() {
        return "Patient Audit Trail: " +
               "ID=" + patientId +
               ", LastModified=" + java.time.LocalDateTime.now() +
               ", Room=" + roomNumber;
    }
}
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

// Doctor class with role-based access
public class Doctor {
    private final String licenseNumber;
    private final String specialty;
    private final Set<String> certifications;
    
    public Doctor(String licenseNumber, String specialty, Set<String> certifications) {
        if (licenseNumber == null || !licenseNumber.matches("^MD\\d{6}$")) {
            throw new IllegalArgumentException("Invalid license number format");
        }
        
        this.licenseNumber = licenseNumber;
        this.specialty = specialty;
        this.certifications = new HashSet<>(certifications != null ? certifications : new HashSet<>());
    }
    
    public String getLicenseNumber() {
        return licenseNumber;
    }
    
    public String getSpecialty() {
        return specialty;
    }
    
    public Set<String> getCertifications() {
        return new HashSet<>(certifications); // Defensive copy
    }
    
    @Override
    public String toString() {
        return "Doctor{license='" + licenseNumber + "', specialty='" + specialty + "'}";
    }
}

// Nurse class with shift-based access
public class Nurse {
    private final String nurseId;
    private final String shift;
    private final List<String> qualifications;
    
    public Nurse(String nurseId, String shift, List<String> qualifications) {
        if (nurseId == null || !nurseId.matches("^RN\\d{4}$")) {
            throw new IllegalArgumentException("Invalid nurse ID format");
        }
        if (!Arrays.asList("DAY", "NIGHT", "SWING").contains(shift)) {
            throw new IllegalArgumentException("Invalid shift type");
        }
        
        this.nurseId = nurseId;
        this.shift = shift;
        this.qualifications = new ArrayList<>(qualifications != null ? qualifications : new ArrayList<>());
    }
    
    public String getNurseId() {
        return nurseId;
    }
    
    public String getShift() {
        return shift;
    }
    
    public List<String> getQualifications() {
        return new ArrayList<>(qualifications); // Defensive copy
    }
    
    @Override
    public String toString() {
        return "Nurse{id='" + nurseId + "', shift='" + shift + "'}";
    }
}

// Administrator class with system-wide access
public class Administrator {
    private final String adminId;
    private final List<String> accessPermissions;
    
    public Administrator(String adminId, List<String> accessPermissions) {
        if (adminId == null || !adminId.matches("^ADM\\d{3}$")) {
            throw new IllegalArgumentException("Invalid admin ID format");
        }
        
        this.adminId = adminId;
        this.accessPermissions = new ArrayList<>(accessPermissions != null ? accessPermissions : new ArrayList<>());
    }
    
    public String getAdminId() {
        return adminId;
    }
    
    public List<String> getAccessPermissions() {
        return new ArrayList<>(accessPermissions); // Defensive copy
    }
    
    @Override
    public String toString() {
        return "Administrator{id='" + adminId + "', permissions=" + accessPermissions.size() + "}";
    }
}
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class HospitalSystem {
    // Private patient registry
    private final Map<String, Object> patientRegistry;
    
    // Static final constants for hospital policies
    private static final String[] PRIVACY_RULES = {
        "HIPAA_COMPLIANCE_REQUIRED",
        "MINIMUM_ACCESS_PRINCIPLE",
        "AUDIT_TRAIL_MANDATORY"
    };
    
    private static final int MAX_PATIENT_CAPACITY = 1000;
    private static final String HOSPITAL_ID = "HSP_001";
    
    public HospitalSystem() {
        this.patientRegistry = new HashMap<>();
    }
    
    // Public method with role validation using instanceof
    public boolean admitPatient(Object patient, Object staff) {
        if (patient == null || staff == null) {
            return false;
        }
        
        // Use instanceof for role validation
        if (!(patient instanceof Patient)) {
            System.out.println("Invalid patient object");
            return false;
        }
        
        if (!validateStaffAccess(staff, patient)) {
            System.out.println("Staff access denied for patient admission");
            return false;
        }
        
        Patient p = (Patient) patient;
        if (patientRegistry.size() >= MAX_PATIENT_CAPACITY) {
            System.out.println("Hospital at maximum capacity");
            return false;
        }
        
        patientRegistry.put(p.getPatientId(), patient);
        System.out.println("Patient admitted successfully: " + p.getPublicInfo());
        return true;
    }
    
    // Private method for privacy protection
    private boolean validateStaffAccess(Object staff, Object patient) {
        // Validate staff type and permissions
        if (staff instanceof Doctor) {
            return true; // Doctors have full access
        } else if (staff instanceof Nurse) {
            // Nurses have limited access based on shift
            return validateNurseAccess((Nurse) staff);
        } else if (staff instanceof Administrator) {
            // Admins have system-wide access
            Administrator admin = (Administrator) staff;
            return admin.getAccessPermissions().contains("PATIENT_ADMISSION");
        }
        
        return false; // Unauthorized staff
    }
    
    private boolean validateNurseAccess(Nurse nurse) {
        // Nurses can admit patients during their shift
        return nurse.getQualifications().contains("PATIENT_CARE") || 
               nurse.getQualifications().contains("EMERGENCY_RESPONSE");
    }
    
    // Package-private methods for internal hospital operations
    Map<String, Object> getPatientRegistry() {
        return new HashMap<>(patientRegistry); // Defensive copy
    }
    
    boolean transferPatient(String patientId, int newRoom, String newPhysician) {
        Object patientObj = patientRegistry.get(patientId);
        if (patientObj instanceof Patient) {
            Patient patient = (Patient) patientObj;
            patient.setRoomNumber(newRoom);
            patient.setAttendingPhysician(newPhysician);
            return true;
        }
        return false;
    }
    
    String generatePatientReport(String patientId, Object staff) {
        if (!validateStaffAccess(staff, null)) {
            return "Access Denied";
        }
        
        Object patientObj = patientRegistry.get(patientId);
        if (patientObj instanceof Patient) {
            Patient patient = (Patient) patientObj;
            
            if (staff instanceof Doctor || staff instanceof Administrator) {
                return patient.getBasicInfo(); // Full access
            } else {
                return patient.getPublicInfo(); // Limited access
            }
        }
        
        return "Patient not found";
    }
    
    // Static method for hospital policies
    public static String[] getPrivacyRules() {
        return PRIVACY_RULES.clone();
    }
    
    public static String getHospitalId() {
        return HOSPITAL_ID;
    }
    
    @Override
    public String toString() {
        return "HospitalSystem{" +
               "totalPatients=" + patientRegistry.size() +
               ", capacity=" + MAX_PATIENT_CAPACITY +
               ", hospitalId='" + HOSPITAL_ID + '\'' +
               '}';
    }
}
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

public class HospitalManagementDemo {
    public static void main(String[] args) {
        // Create hospital system
        HospitalSystem hospital = new HospitalSystem();
        
        // Create medical staff
        Doctor doctor = new Doctor("MD123456", "Cardiology", 
            new HashSet<>(Arrays.asList("Board Certified", "Emergency Care")));
        
        Nurse nurse = new Nurse("RN1234", "DAY", 
            Arrays.asList("PATIENT_CARE", "MEDICATION_ADMIN"));
        
        Administrator admin = new Administrator("ADM001", 
            Arrays.asList("PATIENT_ADMISSION", "SYSTEM_ACCESS"));
        
        // Create immutable medical record
        MedicalRecord medRecord = new MedicalRecord(
            "MR001",
            "ATCGATCGATCG",
            new String[]{"Penicillin", "Latex"},
            new String[]{"Diabetes", "Hypertension"},
            LocalDate.of(1990, 5, 15),
            "A+"
        );
        
        // Create patient with full information
        Patient patient = new Patient(
            "P001",
            "John Doe",
            medRecord,
            "Jane Doe - 555-1234",
            "BlueCross - Policy 12345",
            101,
            "Dr. Smith"
        );
        
        // Test admission with different staff types
        System.out.println("=== Patient Admission Tests ===");
        System.out.println("Doctor admission: " + hospital.admitPatient(patient, doctor));
        
        // Create emergency patient
        Patient emergencyPatient = new Patient("Emergency Patient");
        System.out.println("Nurse emergency admission: " + 
            hospital.admitPatient(emergencyPatient, nurse));
        
        // Test access control
        System.out.println("\n=== Access Control Tests ===");
        System.out.println("Doctor accessing patient: " + 
            hospital.generatePatientReport("P001", doctor));
        System.out.println("Nurse accessing patient: " + 
            hospital.generatePatientReport("P001", nurse));
        
        // Test immutable medical record
        System.out.println("\n=== Medical Record Tests ===");
        System.out.println("Patient allergic to Penicillin: " + 
            medRecord.isAllergicTo("Penicillin"));
        System.out.println("Patient allergic to Aspirin: " + 
            medRecord.isAllergicTo("Aspirin"));
        
        // Test privacy rules
        System.out.println("\n=== Privacy Compliance ===");
        System.out.println("Hospital Privacy Rules: " + 
            Arrays.toString(HospitalSystem.getPrivacyRules()));
        
        System.out.println("\n=== System Status ===");
        System.out.println(hospital);
    }
}
