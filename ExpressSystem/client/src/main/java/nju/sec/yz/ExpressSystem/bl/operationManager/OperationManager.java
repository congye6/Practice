package nju.sec.yz.ExpressSystem.bl.operationManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class OperationManager {
	private static final int CAN_NOT_UNDO_INDEX = -1; 
    private static final int REDO_UNDO_INDEX_INTERVAL = 1; 
    private List<UndoableOperation> operationList; 
    private int undoIndex; 

    private OperationManager(List<UndoableOperation> list) { 
        operationList = list; 
    } 

    public static OperationManager getInstance() { 
        return new OperationManager(new ArrayList<UndoableOperation>()); 
    } 
 

    public void addOperation(UndoableOperation operation) { 
        operation.execute(); 
        operationList.add(operation); 
        setUndoIndex(getLastIndex()); 
    } 

    private void removeOperation(UndoableOperation operation) { 
        operationList.remove(operation); 
        undoIndex--; 
    } 

    private void undooperation(UndoableOperation operation) { 
        operation.undo(); 
        undoIndex--; 
    } 

    private void redooperation(UndoableOperation operation) { 
        operation.redo(); 
        undoIndex++; 
    } 

    public boolean canUndo() { 
        return undoIndex > CAN_NOT_UNDO_INDEX; 
    } 

    public boolean canRedo() { 
        return undoIndex < getLastIndex(); 
    } 

    public void undo() { 
        if (!canUndo()) { 
            throw new CannotUndoException(); 
        } 
        UndoableOperation current = operationList.get(undoIndex); 
        undooperation(current); 
        for (int i = undoIndex; i >= 0; i--) { 
            UndoableOperation temp = operationList.get(i); 
            if (!temp.canUndo()) { 
                removeOperation(temp); 
            } else if (current.canAppendWith(temp)) { 
                undooperation(temp); 
                current = temp; 
            } else { 
                setUndoIndex(i); 
                break; 
            } 
        } 
    } 

    public void redo() { 
        if (!canRedo()) { 
            throw new CannotRedoException(); 
        } 
        int redoIndex = getRedoIndex(); 
        UndoableOperation current = operationList.get(redoIndex); 
        redooperation(current); 
        
    } 

    public void reset() { 
        operationList.clear(); 
        setUndoIndex(CAN_NOT_UNDO_INDEX); 
    } 

    private void setUndoIndex(int index) { 
        undoIndex = index; 
    } 

    private int getLastIndex() { 
        return operationList.size() - 1; 
    } 

    private int getRedoIndex() { 
        return undoIndex + REDO_UNDO_INDEX_INTERVAL; 
    } 
}
