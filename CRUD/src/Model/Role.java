package Model;

public enum Role {
    MANAGER("Manager"),
    CHEF_DE_PROJET("Chef de projet"),
    DIRECTEUR("Directeur"),
    Employee("Employee");
    
    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}