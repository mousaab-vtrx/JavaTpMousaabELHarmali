package Model;

public enum TypeConge {
    Conge_Paye("Conge Paye"),
    Conge_Non_Paye("Conge Non Paye"),
    Conge_Maladie("Conge Maladie");
    
    private final String description;

    TypeConge(String description) {
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
