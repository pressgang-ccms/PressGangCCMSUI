package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * The presneter that manages the topic search screen, combining the tags, fields and filters views
 * @author Matthew Casperson
 */
@Dependent
public class SearchTagsFieldsAndFiltersPresenter implements TemplatePresenter {
    
    /** The history token used to access this page */
    public static final String HISTORY_TOKEN = "SearchTagsFieldsAndFiltersView";
    
    /** The view that displays the tag options */
    @Inject private SearchPresenter.Display tagsDisplay;
    /** The view that displays the field options */
    @Inject private SearchFieldPresenter.Display fieldsDisplay;
    
    @Override
    public void go(final HasWidgets container) {
     
    }
    
    @Override
    public void parseToken(final String historyToken) {
        
    }
}
