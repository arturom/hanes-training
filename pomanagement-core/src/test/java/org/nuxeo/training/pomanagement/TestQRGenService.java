package org.nuxeo.training.pomanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.platform.test.PlatformFeature;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.PartialDeploy;
import org.nuxeo.runtime.test.runner.TargetExtensions;

import javax.inject.Inject;

@RunWith(FeaturesRunner.class)
@Features({ PlatformFeature.class })
@Deploy("org.nuxeo.training.pomanagement.pomanagement-core")
@PartialDeploy(bundle = "studio.extensions.amejia-SANDBOX", extensions = { TargetExtensions.ContentModel.class })
public class TestQRGenService {

    @Inject
    protected QRGenService qrgenservice;
    
    @Inject
    protected CoreSession session;

    @Test
    public void testService() {
        assertNotNull(qrgenservice);
    }
    
    @Test
    public void testBuildQRCode() {
    	DocumentModel doc = session.createDocumentModel("/", "PO-test", "PurchaseOrder");
    	
    	doc.setPropertyValue("dc:title", "PO-test");
    	doc.setPropertyValue("purchaseorder:price", 250.0f);

    	String result = qrgenservice.buildQRCode(doc);
    	
    	assertEquals("PO-test;250.0", result);
    	
    }
}
