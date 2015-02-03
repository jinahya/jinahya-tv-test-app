/*
 * Copyright 2015 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.github.jinahya.test;


import com.github.jinahya.media.Players;
import com.github.jinahya.tv.xlet.XletContextHolder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.media.Player;
import javax.tv.service.selection.ServiceContextException;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;
import net.sf.microlog.core.Logger;
import net.sf.microlog.core.LoggerFactory;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class Application implements Xlet {


    private static final Logger logger
        = LoggerFactory.getLogger(Application.class);


    public void initXlet(final XletContext xletContext)
        throws XletStateChangeException {

        logger.debug("initXlet(" + xletContext + ")");

        XletContextHolder.set(xletContext);
    }


    public void startXlet() throws XletStateChangeException {

        logger.debug("startXlet()");

        {
            logger.debug("collecting players...");
            final List players = new ArrayList();
            try {
                Players.collect(XletContextHolder.get(), players);
            } catch (final ServiceContextException sce) {
                logger.error("failed to collect players", sce);
            }
            for (final Iterator i = players.iterator(); i.hasNext();) {
                final Player player = (Player) i.next();
                logger.debug("player: " + player);
            }
        }
    }


    public void pauseXlet() {

        logger.debug("pauseXlet()");
    }


    public void destroyXlet(boolean unconditional)
        throws XletStateChangeException {

        logger.debug("destroyXlet(" + unconditional + ")");

        XletContextHolder.set(null);
    }


}

