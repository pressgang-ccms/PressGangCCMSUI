/*
  Copyright 2011-2014 Red Hat

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;

/**
 * The base class for views that display a collection of ordered children and a second collection of possible children
 *
 * @param <T> The entity type
 * @param <A> The possible child type
 * @param <B> The collection type for entity A
 * @param <C> The collection item type for entity A
 * @param <D> The existing child type
 * @param <E> The collection type for entity D
 * @param <F> The collection item type for entity D
 * @author Matthew Casperson
 */
abstract public class BaseOrderedChildrenView<
        T extends RESTBaseEntityV1<T, ?, ?>,
        A extends RESTBaseEntityV1<A, B, C>, B extends RESTBaseEntityCollectionV1<A, B, C>, C extends RESTBaseEntityCollectionItemV1<A, B, C>,
        D extends RESTBaseEntityV1<D, E, F>, E extends RESTBaseEntityCollectionV1<D, E, F>, F extends RESTBaseEntityCollectionItemV1<D, E, F>>
        extends BaseExtendedChildrenView<T, C, D, E, F> implements BaseOrderedChildrenViewInterface<T, C, D, E, F> {

    public BaseOrderedChildrenView(final String applicationName, final String pageName) {
        super(applicationName, pageName);
    }
}
