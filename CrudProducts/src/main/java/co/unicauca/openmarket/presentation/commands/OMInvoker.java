/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.presentation.commands;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brayan majin, julian ruano
 */
public class OMInvoker {
    
    private List<OMCommand> myCommands;
    private List<OMCommand> deleteCommands;
    private OMCommand currentCommand;
    
    public OMInvoker(){
      
         myCommands = new ArrayList<>();
        deleteCommands = new ArrayList<>();
        currentCommand=null;
        
       
    }
    
    public void addCommand(OMCommand actualCommand){
        currentCommand = actualCommand;
        
    }
    
    public void execute(){
        this.addCommand(currentCommand);
        currentCommand.make();
        myCommands.add(currentCommand);
    }
    
    public void unexecute(){
        if (!myCommands.isEmpty()){
            int index = myCommands.size()-1;
            OMCommand command= myCommands.get(index);
            command.unmake();
            deleteCommands.add(command);
            myCommands.remove(index);
        }
        
    }
    
    public void reExecuted(){
        if (!deleteCommands.isEmpty()){
            int index = deleteCommands.size()-1;
            OMCommand command= deleteCommands.get(index);
            command.make();
            myCommands.add(command);
            deleteCommands.remove(index);
        }
    }
    
    
    public boolean hasMoreCommands(){
        return !myCommands.isEmpty();
    }
    public boolean hasMoreCommandsRedo(){
        return !deleteCommands.isEmpty();
    }
    
}
