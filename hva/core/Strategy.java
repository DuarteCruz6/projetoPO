package hva.core;

import java.io.Serializable;

public interface Strategy extends Serializable{
    //implements strategy when calculating an employee's satisfaction
    double execute(ParametrosSatisfacao parametros);
}