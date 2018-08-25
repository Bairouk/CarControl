package stage.ensias.com.carcontrol;

import java.io.Serializable;

public class carsInfo implements Serializable {

    public String agent;
    public String carId;


    public int done;
    public int inprogress;

    public int line;
    public String owner;
    public String problem;
    public String type_controle;


    public carsInfo(String agent, String carId, int done, int inprogress, int line, String owner, String problem, String type_controle) {
        this.agent = agent;
        this.carId = carId;
        this.done = done;
        this.inprogress = inprogress;
        this.line = line;
        this.owner = owner;
        this.problem = problem;
        this.type_controle = type_controle;
    }

    public carsInfo() {

    }


}
