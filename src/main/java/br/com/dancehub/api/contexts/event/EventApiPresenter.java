package br.com.dancehub.api.contexts.event;

import br.com.dancehub.api.contexts.event.category.CategoryApiPresenter;
import br.com.dancehub.api.contexts.event.models.EventResponse;

public interface EventApiPresenter {

    static EventResponse present(EventEntity entity) {
        return new EventResponse(
                entity.getId().toString(),
                entity.getName(),
                entity.getDescription(),
                entity.getPlace(),
                entity.getTime(),
                entity.getCategories().stream().map(CategoryApiPresenter::present).toList()
        );
    }


}
