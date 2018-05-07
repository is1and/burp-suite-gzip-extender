package burp;

import java.util.List;

import javax.swing.SwingUtilities;

import java.awt.Component;
import java.io.PrintWriter;

public class BurpExtender implements IBurpExtender, ITab, IHttpListener, IProxyListener {

	private IExtensionHelpers helpers;
	private static PrintWriter stdout;

	@Override
	public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {

		callbacks.setExtensionName("gzip");
		callbacks.registerHttpListener(this);
		callbacks.registerProxyListener(this);
		helpers = callbacks.getHelpers();
		stdout = new PrintWriter(callbacks.getStdout(), true);
		
		callbacks.registerContextMenuFactory(new menu(this.helpers));
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				stdout.println("stupid windows");
			}
		});
	}

	@Override
	public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) {
		
	}

	@Override
	public String getTabCaption() {

		return null;
	}

	@Override
	public Component getUiComponent() {

		return null;
	}

	@Override
	public void processProxyMessage(boolean messageIsRequest, IInterceptedProxyMessage message) {
		
	}
	
	public static void main(String[] args) {
		stdout.println("stupid windows");
	}

}