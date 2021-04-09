package nl.tudelft.oopp.g72.entities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MultipleSelectionModel;

/**
 * Class holding functionality regarding QuestionListSelectionModel.
 *
 * @param <T> T
 */
public class QuestionListSelectionModel<T> extends MultipleSelectionModel<T> {

    /**
     * Getter to get selected idices.
     *
     * @return list of selected idices
     */
    @Override
    public ObservableList<Integer> getSelectedIndices() {
        return FXCollections.emptyObservableList();
    }

    /**
     * Getter to get selected items.
     *
     * @return list of selected items
     */
    @Override
    public ObservableList<T> getSelectedItems() {
        return FXCollections.emptyObservableList();
    }

    /**
     * Selects index and indices.
     *
     * @param index index
     * @param indices indices
     */
    @Override
    public void selectIndices(int index, int... indices) {
    }

    /**
     * Select all.
     */
    @Override
    public void selectAll() {
    }

    /**
     * Select first index.
     */
    @Override
    public void selectFirst() {
    }

    /**
     * Select last index.
     */
    @Override
    public void selectLast() {
    }

    /**
     * Clear and select index.
     *
     * @param index index
     */
    @Override
    public void clearAndSelect(int index) {
    }

    /**
     * Select index.
     *
     * @param index index
     */
    @Override
    public void select(int index) {
    }

    /**
     * Select object.
     *
     * @param obj object
     */
    @Override
    public void select(T obj) {
    }

    /**
     * Clear selection index.
     *
     * @param index index
     */
    @Override
    public void clearSelection(int index) {
    }

    /**
     * Clear selection.
     */
    @Override
    public void clearSelection() {
    }

    /**
     * States whether index has been selected.
     *
     * @param index index
     * @return boolean value
     */
    @Override
    public boolean isSelected(int index) {
        return false;
    }

    /**
     * Checks if empty.
     *
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return true;
    }

    /**
     * Select previous index.
     */
    @Override
    public void selectPrevious() {
    }

    /**
     * Select next index.
     */
    @Override
    public void selectNext() {
    }
}