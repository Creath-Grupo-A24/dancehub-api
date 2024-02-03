package br.com.dancehub.api.contexts.event.category;

public interface CategoryApiPresenter {

    static CategoryResponse present(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getType().name()
        );
    }

}
