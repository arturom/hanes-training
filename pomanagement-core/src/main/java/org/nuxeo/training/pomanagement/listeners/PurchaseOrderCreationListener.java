package org.nuxeo.training.pomanagement.listeners;


import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.event.Event;
import org.nuxeo.ecm.core.event.EventContext;
import org.nuxeo.ecm.core.event.EventListener;

import org.nuxeo.ecm.core.event.impl.DocumentEventContext;

public class PurchaseOrderCreationListener implements EventListener {
  

    @Override
    public void handleEvent(Event event) {
        EventContext ctx = event.getContext();
        if (!(ctx instanceof DocumentEventContext)) {
          return;
        }

        DocumentEventContext docCtx = (DocumentEventContext) ctx;
        DocumentModel doc = docCtx.getSourceDocument();

        // Add some logic starting from here.
        
        if (doc.getType().equals("PurchaseOrder")) {
        	doc.setPropertyValue("dc:title", "PO-Auto-generated-title");
        }
    }
}
