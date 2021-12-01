package champollion;

import java.util.ArrayList;

public class Enseignant extends Personne {

    ArrayList<ServicePrevu> servicesPrevus = new ArrayList<ServicePrevu>();
    ArrayList<Intervention> lesInterventions = new ArrayList<Intervention>();

    public Enseignant(String nom, String email) {
        super(nom, email);
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        float res = 0;
        for(ServicePrevu sp : servicesPrevus){  
            res =  (float) (res + sp.getVolumeCM()*1.5);
            res = res + sp.getVolumeTD();
            res = (float) (res + sp.getVolumeTP()*0.75);
        }
        return Math.round(res);
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        float res = 0;
        for(ServicePrevu sp : servicesPrevus){
            if(sp.getEnseignement().equals(ue)){
                res =  (float) (res + sp.getVolumeCM()*1.5);
                res = res + sp.getVolumeTD();
                res = (float) (res + sp.getVolumeTP()*0.75);
            }
        }
        return Math.round(res);
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        boolean dejaPresent = false;
        //On regarde si le prof a déjà des heures dans l'ue en question
        for(ServicePrevu sp : servicesPrevus){
            if(sp.getEnseignement().equals(ue)){
                dejaPresent = true;
                sp.setVolumeCM(sp.getVolumeCM()+volumeCM);
                sp.setVolumeTD(sp.getVolumeTD()+volumeTD);
                sp.setVolumeTP(sp.getVolumeTP()+volumeTP);
            }
        }
        if(!dejaPresent){
            servicesPrevus.add(new ServicePrevu(ue, volumeCM, volumeTD, volumeTP));
        }
    }
    
    /**
     * Ajoute une intervention à la liste des interventions de l'enseignant
     * @param intervention
     */
    public void ajouterIntervention(Intervention intervention){
        boolean inter_valide = false;
        for(ServicePrevu sp : servicesPrevus){
            if(sp.getEnseignement().equals(intervention.getUE())){
                inter_valide = true;
            }
        }
        if(!inter_valide){
            throw new IllegalArgumentException("Intervention n'a pas un ue valide");
        }
        lesInterventions.add(intervention);
    }

    /**
     * Retourne le nombre d'heure qu'il reste a planifier pour un enseignant dans un UE pour un type de cours
     * @param ue
     * @param type
     * @return
     */
    public int resteAPlanifier(UE ue, TypeIntervention type){
        //on verifie que l'ue en parametre est un ue pris en charge par le prof
        boolean ue_valide = false;
        for(ServicePrevu sp1 : servicesPrevus){
            if(sp1.getEnseignement().equals(ue)){
                ue_valide = true;
            }
        }
        if(!ue_valide){
            throw new IllegalArgumentException("cette ue n'est pas enseigné par le prof");
        }

        int nb_heure_ue = 0;
        int nb_heure_planifiee = 0;
        //on cherche le nombre d'heure prevu dans l'ue en question
        for(ServicePrevu sp : servicesPrevus){
            if(sp.getEnseignement().equals(ue)){
                switch (type) {
                    case CM:
                        nb_heure_ue = sp.getVolumeCM();
                    case TD:
                        nb_heure_ue = sp.getVolumeTD();
                    case TP:
                        nb_heure_ue = sp.getVolumeTP();
                    default:
                        break;
                }
            }
        }
        //on cherche le nombre d'heure réalisée
        for(Intervention i : lesInterventions){
            if(i.getUE().equals(ue) && i.getType()==type){
                nb_heure_planifiee = nb_heure_planifiee + i.getDuree();
            }
        }
        return nb_heure_ue - nb_heure_planifiee;
    }
    
    /**
     * 
     * @return
     */
    public boolean enSousService(){
        return (this.heuresPrevues()<192);
    }

    /**
     * 
     * @return
     */
    public ArrayList<Intervention> gInterventions(){
        return lesInterventions;
    }


    

}
