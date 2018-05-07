package burp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;

import gziputil.gzip;

public class menu implements IContextMenuFactory {

	IExtensionHelpers helpers;
	
	public menu(IExtensionHelpers helpers) {
		this.helpers = helpers;
	}

	@Override
	public List<JMenuItem> createMenuItems(IContextMenuInvocation invocation) {
		List<JMenuItem> list = new ArrayList();
		JMenuItem jMenuItem1 = new JMenuItem("request gzip");
		list.add(jMenuItem1);

		JMenuItem jMenuItem2 = new JMenuItem("request gunzip");
		list.add(jMenuItem2);
		
		JMenuItem jMenuItem3 = new JMenuItem("response gzip");
		list.add(jMenuItem3);
		JMenuItem jMenuItem4 = new JMenuItem("response gunzip");
		list.add(jMenuItem4);

		jMenuItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				IHttpRequestResponse[] message = invocation.getSelectedMessages();

				byte[] req = message[0].getRequest();
				
				IRequestInfo iRequestInfo = helpers.analyzeRequest(req);
				int reqpos = iRequestInfo.getBodyOffset();

				byte[] reqbody = new byte[req.length - reqpos];
				
				System.arraycopy(req, reqpos, reqbody, 0, req.length - reqpos);

				
				try {
					
					byte[] newreqbody = gzip.compress(reqbody);

					byte[] newreq = new byte[reqpos + newreqbody.length];
					
					System.arraycopy(req, 0, newreq, 0, reqpos);
					System.arraycopy(newreqbody, 0, newreq, reqpos, newreqbody.length);
					
					message[0].setRequest(newreq);

					
					
					
				} catch (Exception e2) {
					e2.getMessage();
				}

			}
		});
		
		jMenuItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				IHttpRequestResponse[] message = invocation.getSelectedMessages();

				byte[] req = message[0].getRequest();
				//byte[] res = message[0].getResponse();
				
				IRequestInfo iRequestInfo = helpers.analyzeRequest(req);
				//IResponseInfo iResponseInfo = helpers.analyzeResponse(res);
				int reqpos = iRequestInfo.getBodyOffset();
				//int respos = iResponseInfo.getBodyOffset();
				

				byte[] reqbody = new byte[req.length - reqpos];
				//byte[] resbody = new byte[res.length - reqpos];

				
				
				System.arraycopy(req, reqpos, reqbody, 0, req.length - reqpos);
				//System.arraycopy(res, respos, resbody, 0, res.length - respos);

				
				
				try {
					
					byte[] newreqbody = gzip.uncompress(reqbody);
					//byte[] newresbody = gzip.compress(resbody);

					byte[] newreq = new byte[reqpos + newreqbody.length];
					//byte[] newres = new byte[respos + newresbody.length];
					
					System.arraycopy(req, 0, newreq, 0, reqpos);
					System.arraycopy(newreqbody, 0, newreq, reqpos, newreqbody.length);
					//System.arraycopy(res, 0, newres, 0, respos);
					//System.arraycopy(newresbody, 0, newres, respos, newresbody.length);
					
					message[0].setRequest(newreq);
					//message[0].setResponse(newres);
					
					
					
				} catch (Exception e2) {
					e2.getMessage();
				}

			}
		});
		
		jMenuItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				IHttpRequestResponse[] message = invocation.getSelectedMessages();

				byte[] res = message[0].getResponse();
				
				
				IResponseInfo iResponseInfo = helpers.analyzeResponse(res);
				int respos = iResponseInfo.getBodyOffset();				

				byte[] resbody = new byte[res.length - respos];
				
				System.arraycopy(res, respos, resbody, 0, res.length - respos);

				
				try {
					
					byte[] newresbody = gzip.compress(resbody);

					byte[] newres = new byte[respos + newresbody.length];
					
					System.arraycopy(res, 0, newres, 0, respos);
					System.arraycopy(newresbody, 0, newres, respos, newresbody.length);
					
					message[0].setResponse(newres);

					
					
					
				} catch (Exception e2) {
					e2.getMessage();
				}

			}
		});
		
		jMenuItem4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				IHttpRequestResponse[] message = invocation.getSelectedMessages();

				byte[] res = message[0].getResponse();
				
				
				IResponseInfo iResponseInfo = helpers.analyzeResponse(res);
				int respos = iResponseInfo.getBodyOffset();				

				byte[] resbody = new byte[res.length - respos];
				
				System.arraycopy(res, respos, resbody, 0, res.length - respos);

				
				try {
					
					byte[] newresbody = gzip.uncompress(resbody);

					byte[] newres = new byte[respos + newresbody.length];
					
					System.arraycopy(res, 0, newres, 0, respos);
					System.arraycopy(newresbody, 0, newres, respos, newresbody.length);
					
					message[0].setResponse(newres);

					
					
					
				} catch (Exception e2) {
					e2.getMessage();
				}

			}
		});
		return list;
	}

}
