package org.nuxeo.training.pomanagement.listeners;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.event.EventService;
import org.nuxeo.ecm.core.event.impl.EventListenerDescriptor;
import org.nuxeo.ecm.platform.test.PlatformFeature;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.PartialDeploy;
import org.nuxeo.runtime.test.runner.TargetExtensions;

import javax.inject.Inject;

@RunWith(FeaturesRunner.class)
@Features({ PlatformFeature.class })
@Deploy({"org.nuxeo.training.pomanagement.pomanagement-core"})
@PartialDeploy(bundle = "studio.extensions.amejia-SANDBOX", extensions = { TargetExtensions.ContentModel.class })
public class TestPurchaseOrderCreationListener {

    protected final List<String> events = Arrays.asList("emptyDocumentModelCreated");

    @Inject
    protected EventService s;
    
    @Inject
    protected CoreSession session;

    @Test
    public void listenerRegistration() {
        EventListenerDescriptor listener = s.getEventListener("purchaseordercreationlistener");
        assertNotNull(listener);
        assertTrue(events.stream().allMatch(listener::acceptEvent));
    }
    
    @Test
    public void testTitleSetAutomatically() {
    	DocumentModel doc = session.createDocumentModel("/", "This title should not matter", "PurchaseOrder");
    	assertEquals("PO-Auto-generated-title", doc.getTitle());
    }
}
