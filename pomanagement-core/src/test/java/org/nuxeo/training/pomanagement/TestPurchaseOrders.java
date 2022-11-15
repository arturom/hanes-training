package org.nuxeo.training.pomanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.automation.test.AutomationFeature;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.test.CoreFeature;
import org.nuxeo.ecm.core.test.DefaultRepositoryInit;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.runtime.RuntimeService;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.PartialDeploy;
import org.nuxeo.runtime.test.runner.RuntimeFeature;
import org.nuxeo.runtime.test.runner.TargetExtensions;

@RunWith(FeaturesRunner.class)
@Features(AutomationFeature.class)
@RepositoryConfig(init = DefaultRepositoryInit.class, cleanup = Granularity.METHOD)
@Deploy("org.nuxeo.training.pomanagement.pomanagement-core")
@PartialDeploy(bundle = "studio.extensions.amejia-SANDBOX", extensions = { TargetExtensions.Automation.class })
public class TestPurchaseOrders {
	
	@Inject
	protected CoreSession session;
	
	@Test
	public void shouldDeployNuxeoRuntime() {
		RuntimeService runtime = Framework.getRuntime();
		assertNotNull(runtime);
	}
	
	@Test
	public void shouldHaveADescription() {
		DocumentModel doc = session.createDocumentModel("/", "PO-10", "PurchaseOrder");
		doc.setPropertyValue("purchaseorder:price", 100.0);
		doc.setPropertyValue("purchaseorder:product", "COMPUTER/COMP_LAPTOP");
		doc.setPropertyValue("purchaseorder:quantity", 1);
		
		doc = session.createDocument(doc);
		String desc = (String) doc.getPropertyValue("dc:description");
		assertEquals("Description set in Event Handler", desc);
	}

}
