package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    String getName();

    String getSearchTerm();

    String getTypeOfContent();

    UUID getId();

    default String getStringRepresentation() {
        return this.getName() + " - " + this.getTypeOfContent();
    }
}
