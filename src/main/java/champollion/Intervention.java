package champollion;

import java.util.Date;

public class Intervention {
    
    private Date debut;
    private int duree;
    private boolean annulee = false;
    private int heureDebut;
    private Salle lieu;
    private TypeIntervention type;
    private Enseignant intervenant;
    private UE ue;


    public Intervention(Date debut, int heureDebut, int duree, Salle lieu, TypeIntervention type, Enseignant intervenant, UE ue) {
        setDebut(debut);
        setDuree(duree);
        setHeureDebut(heureDebut);
        setLieu(lieu);
        setType(type);
        setUE(ue);

    }


    public Date getDebut() {
        //pas de fuite de reference
        return (Date) this.debut.clone();
    }

    public void setDebut(Date debut) {
        if(null == debut){
            throw new IllegalArgumentException("Date de debut ne peut être nulle");
        }
        this.debut = debut;
    }

    public int getDuree() {
        return this.duree;
    }

    public void setDuree(Integer duree) {
        if(null == duree){
            throw new IllegalArgumentException("Duree de l'intervention ne peut être nulle");
        }
        this.duree = duree;
    }

    public boolean isAnnulee() {
        return this.annulee;
    }

    public void setAnnulee(boolean annulee) {
        this.annulee = annulee;
    }

    public int getHeureDebut() {
        return this.heureDebut;
    }

    public void setHeureDebut(Integer heureDebut) {
        if(null == heureDebut){
            throw new IllegalArgumentException("L'heure de debut de l'intervention ne peut être nulle");
        }
        this.heureDebut = heureDebut;
    }

    public Salle getLieu() {
        return this.lieu;
    }

    public void setLieu(Salle lieu) {
        if(null == lieu){
            throw new IllegalArgumentException("une intervention ne peut avoir un lieu null");
        }
        this.lieu = lieu;
    }

    public TypeIntervention getType() {
        return this.type;
    }

    public void setType(TypeIntervention type) {
        if(null == type){
            throw new IllegalArgumentException("une intervention ne peut avoir un type null");
        }
        this.type = type;
    }

    public Enseignant getIntervenant(){
        return this.intervenant;
    }

    public void setIntervenant(Enseignant intervenant){
        if(null == intervenant){
            throw new IllegalArgumentException("Une intervention doit avoir un enseignant");
        }
        this.intervenant = intervenant;
    }

    public UE getUE(){
        return this.ue;
    }

    public void setUE(UE ue){
        if(null == ue){
            throw new IllegalArgumentException("Une intervention doit avoir un ue");
        }
        this.ue = ue;
    }

}
