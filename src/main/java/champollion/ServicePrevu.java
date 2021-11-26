package champollion;

public class ServicePrevu {
	
    private UE enseignement;
    private int volumeCM;
    private int volumeTD;
    private int volumeTP;

    public ServicePrevu(UE enseignement, Integer volumeCM, Integer volumeTD, Integer volumeTP) {
        if(enseignement == null || volumeCM == null || volumeTD == null || volumeTP == null){
            throw new IllegalArgumentException();
        }
        this.enseignement = enseignement;
        this.volumeCM = volumeCM;
        this.volumeTD = volumeTD;
        this.volumeTP = volumeTP;
    }

    public UE getEnseignement() {
        return enseignement;
    }

    public void setEnseignement(UE enseignement) {
        if(enseignement == null){
            throw new IllegalArgumentException();
        }
        this.enseignement = enseignement;
    }

    public int getVolumeCM() {
        return volumeCM;
    }

    public void setVolumeCM(Integer volumeCM) {
        if(volumeCM == null){
            throw new IllegalArgumentException();
        }
        this.volumeCM = volumeCM;
    }

    public int getVolumeTD() {
        return volumeTD;
    }

    public void setVolumeTD(Integer volumeTD) {
        if(volumeTD == null){
            throw new IllegalArgumentException();
        }
        this.volumeTD = volumeTD;
    }

    public int getVolumeTP() {
        return volumeTP;
    }

    public void setVolumeTP(Integer volumeTP) {
        if(volumeTP == null){
            throw new IllegalArgumentException();
        }
        this.volumeTP = volumeTP;
    }
    
    
    
    

}
