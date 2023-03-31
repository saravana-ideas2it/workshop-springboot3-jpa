package com.ideas2it.workshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SearchParameters {
    private int limit;
    private int page;
    private String sort;
}
