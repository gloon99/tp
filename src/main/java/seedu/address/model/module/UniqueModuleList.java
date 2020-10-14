package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.module.exceptions.ModuleNotFoundException;

public class UniqueModuleList implements Iterable<Module> {

    private final ObservableList<Module> internalList = FXCollections.observableArrayList();
    private final ObservableList<Module> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent module as the given argument.
     */
    public boolean contains(Module newMod) {
        requireNonNull(newMod);
        return internalList.stream().anyMatch(newMod::isSameModule);
    }

    /**
     * Returns true if the list contains a meeting with the same meeting name.
     */
    public boolean contains(ModuleName toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(module -> module.isSameName(toCheck));
    }



    /**
     * Adds a module to the module list.
     * The module must not already exist in the list.
     */
    public void add(Module newMod) throws DuplicateModuleException {
        requireNonNull(newMod);
        if (contains(newMod)) {
            throw new DuplicateModuleException();
        }
        internalList.add(newMod);
    }

    /**
     * Replaces the module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in the list.
     * The module identity of {@code editedModule} must not be the same as another existing module in the list.
     */
    public void setModule(Module target, Module editedModule) throws RuntimeException {
        requireAllNonNull(target, editedModule);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ModuleNotFoundException();
        }
        if (!target.isSameModule(editedModule) && contains(editedModule)) {
            throw new DuplicateModuleException();
        }

        internalList.set(index, editedModule);
    }

    /**
     * Removes the equivalent module from the list.
     * The meeting must exist in the list.
     */
    public void remove(Module toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ModuleNotFoundException();
        }
    }

    public void setModules(UniqueModuleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setModules(List<Module> modules) {
        requireAllNonNull(modules);
        if (!isAllModuleUnique(modules)) {
            throw new DuplicateModuleException();
        }

        internalList.setAll(modules);
    }

    public Optional<Module> getModule(ModuleName name) {
        return internalList.stream().filter(mod->mod.getModuleName().equals(name)).findFirst();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Module> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Module> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueModuleList // instanceof handles nulls
                && internalList.equals(((UniqueModuleList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Checks to make sure all modules are unique within the list
     */
    private boolean isAllModuleUnique(List<Module> mods) {
        for (int i = 0; i < mods.size() - 1; i++) {
            for (int j = i + 1; j < mods.size(); j++) {
                if (mods.get(i).isSameModule(mods.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

