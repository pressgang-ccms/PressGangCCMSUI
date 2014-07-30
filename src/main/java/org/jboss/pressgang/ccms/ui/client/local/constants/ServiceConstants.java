/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.constants;

import org.jboss.pressgang.ccms.utils.constants.CommonConstants;
import org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants;

/**
 * This class holds constants that relate to the underlying REST service.
 *
 * @author Matthew Casperson
 */
public final class ServiceConstants {

    /**
     * The id of the tag applied to all content specs.
     */
    public static final Integer CSP_TAG_ID = 268;
    /**
     * When a spec has no conditions defined, it will actually match a special condition
     * called "default".
     */
    public static final String DEFAULT_CONDITION = "default";

    public static String CS_NODE_TOPIC_TYPES_QUERY = "query;" + CommonFilterConstants.CONTENT_SPEC_NODE_TYPE_FILTER_VAR + "=" +
            CommonConstants.CS_NODE_TOPIC + "," + CommonConstants.CS_NODE_INITIAL_CONTENT_TOPIC + "," + CommonConstants.CS_NODE_META_DATA_TOPIC +";";

    public static String CS_NODE_METADATA_QUERY = "query;" + CommonFilterConstants.CONTENT_SPEC_NODE_TYPE_FILTER_VAR + "=" +
            CommonConstants.CS_NODE_META_DATA + ";";

    /*
        *************** TOPIC CONSTANTS ***************
     */
    public static enum HELP_TOPICS {
        WELCOME_VIEW_CONTENT_TOPIC(12556),
        SHOW_HIDE_SEARCH_RESULTS_TOPIC(21174),
        HOME_VIEW_TOPIC(21175),
        DOCBUILDER_VIEW_TOPIC(21176),
        CREATE(21177),
        SEARCH_TOPICS_VIEW(21178),
        SEARCH_TRANSLATIONS_VIEW(21179),
        IMAGES_VIEW(21180),
        TAGS_VIEW(21181),
        CATEGORIES_VIEW(21182),
        PROJECTS_VIEW(21183),
        REPORTS(21184),
        CREATE_BUG(21185),
        FILES(21200),
        ENTITIES(21201),
        ADVANCED(21186),
        SEARCH(21202),
        SEARCH_CONTENT_SPECS(21203),
        CREATE_CONTENT_SPEC(21204),
        BULK_TAGGING(21206),
        STRING_CONSTANTS(21208),
        BLOB_CONSTANTS(21209),
        INTEGER_CONSTANTS(21210),
        EXTENDED_PROPERTIES(21211),
        EXTENDED_PROPERTY_CATEGORIES(21212),
        MONITORING(21213),
        SIMPLE_SEARCH(21214),
        HELP_MODE(21226),
        SERVER_SELECTION(21227),
        BUILD_LABEL(21228),
        TOPIC_SEARCH_RESULTS(21229),
        XML_EDITOR(21230),
        RENDERED_PREVIEW(21231),
        TOPIC_XML_EDITOR(21233),
        TOPIC_PROPERTIES(21234),
        TOPIC_EXTENDED_PROPERTIES(21235),
        TOPIC_SOURCE_URLS(21236),
        TOPIC_TAGS(21237),
        TOPIC_BUGS(21238),
        TOPIC_REVISIONS(21239),
        TOPIC_SAVE(21240),
        TOPIC_LINE_WRAP(21241),
        TOPIC_HIDDEN_CHARACTERS(21242),
        TOPIC_RENDERED_PANE(21232),
        TOPIC_CONTENT_SPECS(21335),
        TOPIC_XML_VALIDATION(21672),
        TOPIC_PROPERTY_TITLE(21351),
        TOPIC_PROPERTY_REST_ENDPOINT(21352),
        TOPIC_PROPERTY_REST_XML_ENDPOINT(21353),
        TOPIC_PROPERTY_WEBDAV_URL(21354),
        TOPIC_PROPERTY_DESCRIPTION(21355),
        TOPIC_PROPERTY_LOCALE(21356),
        TOPIC_AVAILABLE_EXTENDED_PROPERTIES(21357),
        TOPIC_EXISTING_EXTENDED_PROPERTIES(21358),
        TOPIC_SOURCE_URLS_LIST(21359),
        TOPIC_ADD_SOURCE_URL(21360),
        TOPIC_TAG_PROJECTS_LIST(21364),
        TOPIC_TAG_CATEGORIES_LIST(21365),
        TOPIC_TAG_TAGS_LIST(21366),
        TOPIC_TAG_EXISTING(21368),
        TOPIC_TAG_ADD(21369),
        BULK_TOPIC_IMPORT(21375),
        BULK_TOPIC_OVERWRITE(21376),
        TOPIC_ATOM_FEED(21380),
        TOPIC_CREATE_TOPIC(21398),
        TOPIC_REVISION_TABLE(21399),
        DIFF_DONE(21400),
        DIFF_CANCEL(21401),
        RENDERED_DIFF_DONE(21402),
        RENDERED_DIFF_NEW_WINDOW(21403),
        TOPIC_DIFF_PANE(21404),
        SEARCH_DOWNLOAD_ZIP(21434),
        SEARCH_DOWNLOAD_CSV(21435),
        SEARCH_PROJECTS_COLUMN(21436),
        SEARCH_CATEGORIES_COLUMN(21437),
        SEARCH_TAGS_TABLE(21438),
        IMAGE_CREATE_IMAGE(21674),
        IMAGE_BULK_IMAGE_UPLOAD(21675),
        IMAGE_ADD_LOCALE(21676),
        IMAGE_REMOVE_LOCALE(21677),
        IMAGE_VIEW_IMAGE(21678),
        IMAGE_FIND_TOPICS(21679),
        IMAGE_SAVE(21680),
        IMAGE_SEARCH(21681),
        IMAGE_ID_FIELD(21687),
        IMAGE_DESCRIPTION_FIELD(21688),
        IMAGE_DOCBOOK_IMAGE_TEMPLATES_TABLE(21689),
        IMAGE_DETAILS_TABLE(21690),
        IMAGE_ID_SEARCH_FIELD(21691),
        IMAGE_DESCRIPTION_SEARCH_FIELD(21692),
        IMAGE_ORIGINAL_FILE_NAME_SEARCH_FIELD(21693),
        CONTENT_SPEC_TEXT_EDITOR(23273),
        CONTENT_SPEC_CONTENT_SPEC(23278),
        CONTENT_SPEC_VALIDATION_MESSAGES(23279),
        CONTENT_SPEC_PROPERTIES(23280),
        CONTENT_SPEC_EXTENDED_PROPERTIES(23276),
        CONTENT_SPEC_TAGS(23274),
        CONTENT_SPEC_REVISIONS(23275),
        CONTENT_SPEC_SAVE(23277),
        CONTENT_SPEC_SHOW_HIDE_SEARCH_RESULTS(23281),
        CONTENT_SPEC_VIEW_IN_DOCBUILDER(30193),
        CONTENT_SPEC_PROCESSES(30194),
        CONTENT_SPEC_ACTIONS(30195);

        private final int id;
        public int getId() {
            return id;
        }
        HELP_TOPICS(final int id) {
            this.id = id;
        }
    }


    /**
     * A private constructor to prevent instantiation.
     */
    private ServiceConstants() {

    }
}
