package champollion;

import java.util.ArrayList;

public class Enseignant extends Personne {

    ArrayList<ServicePrevu> servicesPrevus = new ArrayList<>();

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
    
    public void ajouterIntervention(){
        
    }
    
    

}
