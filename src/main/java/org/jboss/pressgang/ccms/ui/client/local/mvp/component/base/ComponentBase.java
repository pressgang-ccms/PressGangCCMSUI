package org.jboss.pressgang.ccms.ui.client.local.mvp.component.base;

import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.CategoriesFilteredResultsAndCategoryViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.ImagesFilteredResultsAndImageViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.SearchTagsFieldsAndFiltersViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.TagsFilteredResultsAndTagViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;

/**
 * Views can either be stand alone, displayed in isolation. Views can also be combined into a presenter to provide a more
 * complex UI.
 * 
 * Because of the One-To_many relationship between presenters and views, the logic that is attached to a view needs to be shared
 * across multiple presenters. Controllers are used to provide the logic that should be applied to a view. This allows presenters
 * to mix and match controllers in much the same way they mix and match views.
 * 
 * @author Matthew Casperson
 * 
 */
abstract public class ComponentBase<T extends BaseTemplateViewInterface> implements Component<T>{

    @Inject
    private HandlerManager eventBus;
    
    protected BaseTemplateViewInterface waitDisplay;
    protected T display; 

    /**
     * Called to bind the UI elements to event handlers.
     *
     * @param display The main template display
     */
    protected void bindStandardButtons(final BaseTemplateViewInterface display, final EditableView editableView) {
        display.getSearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (editableView.checkForUnsavedChanges())
                    eventBus.fireEvent(new SearchTagsFieldsAndFiltersViewEvent());
            }
        });

        display.getBug().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                Window.open(Constants.BUGZILLA_URL, "_blank", "");
            }
        });

        display.getImages().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (editableView.checkForUnsavedChanges())
                    eventBus.fireEvent(new ImagesFilteredResultsAndImageViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX));
            }
        });

        display.getTags().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (editableView.checkForUnsavedChanges())
                    eventBus.fireEvent(new TagsFilteredResultsAndTagViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX));
            }
        });

        display.getCategories().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (editableView.checkForUnsavedChanges())
                    eventBus.fireEvent(new CategoriesFilteredResultsAndCategoryViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX));
            }
        });

        display.getAdvanced().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                display.getShortCutPanelParent().setWidget(display.getAdvancedShortcutPanel());
            }
        });

        final ClickHandler closeAdvancedMenu = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                display.getShortCutPanelParent().setWidget(display.getShortcutPanel());
            }
        };

        display.getAdvancedOpen().addClickHandler(closeAdvancedMenu);
        display.getClose().addClickHandler(closeAdvancedMenu);
    }
    
    @Override
    public void bind(final T display, final BaseTemplateViewInterface waitDisplay)
    {
        this.display = display;
        this.waitDisplay = waitDisplay;
        bindStandardButtons(display, waitDisplay);
    }
    
    @Override
    public void setFeedbackLink(final String pageId)
    {
        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + pageId);
    }
}
