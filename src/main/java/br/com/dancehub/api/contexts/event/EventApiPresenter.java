package br.com.dancehub.api.contexts.event;

public interface EventApiPresenter {

    static EventResponse present(EventEntity entity) {
        return new EventResponse(
                entity.getId().toString(),
                entity.getName(),
                entity.getDescription(),
                entity.getPlace(),
                entity.getTime(),
                entity.getCategories().stream().map(category -> category.getType().name()).toList()
        );
    }


}
