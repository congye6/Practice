package nju.sec.yz.ExpressSystem.bl.operationManager;

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public abstract class UndoableOperation {
	protected boolean canUndo; 

	/**
	 * 具体操作
	 */
    protected abstract void command(); 
    
    /**
     * 恢复操作
     */
    protected abstract void restore(); 

    public void execute() { 
        command(); 
        setCanUndo(true); 
    } 

    protected void unExecute() { 
        restore(); 
        setCanUndo(false); 
    } 

    public void undo() { 
        if (!canUndo()) { 
            throw new CannotUndoException(); 
        } 
        unExecute(); 
    } 

    public void redo() { 
        if (!canRedo()) { 
            throw new CannotRedoException(); 
        } 
        execute(); 
    } 

    public boolean canUndo() { 
        return canUndo; 
    } 

    public boolean canRedo() { 
        return !canUndo; 
    } 

    protected void setCanUndo(boolean canUndo) { 
        this.canUndo = canUndo; 
    } 

    
}
