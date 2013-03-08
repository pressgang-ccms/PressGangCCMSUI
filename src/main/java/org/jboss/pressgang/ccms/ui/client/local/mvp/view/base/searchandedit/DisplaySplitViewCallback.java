package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit;

/**
 * Created with IntelliJ IDEA.
 * User: matthew
 * Date: 3/8/13
 * Time: 11:48 AM
 * To change this template use File | Settings | File Templates.
 */
public interface DisplaySplitViewCallback {
    /**
     * When working with a split panel, all the panels added around the edge before
     * the final panel is added to the middle. This callback provides the opportunity to
     * add those edge panels.
     */
    void addToCompassPoints();
}
