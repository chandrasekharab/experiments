/*
 * $Id$
 *
 * Copyright (c) 2014  Pegasystems Inc.
 * All rights reserved.
 *
 * This  software  has  been  provided pursuant  to  a  License
 * Agreement  containing  restrictions on  its  use.   The  software
 * contains  valuable  trade secrets and proprietary information  of
 * Pegasystems Inc and is protected by  federal   copyright law.  It
 * may  not be copied,  modified,  translated or distributed in  any
 * form or medium,  disclosed to third parties or used in any manner
 * not provided for in  said  License Agreement except with  written
 * authorization from Pegasystems Inc.
*/

package mail;
import java.util.*;

import javax.mail.*;
import javax.mail.search.FlagTerm;

import printer.PrinterService;

import com.sun.xml.internal.messaging.saaj.util.Base64;

public class GmailReader {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("imap.gmail.com", "bsekhara@gmail.com", Base64.base64Decode("YmNzcmFvMDg="));
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);
          //  inbox.getU
            //Message msg = inbox.getMessage(inbox.getMessageCount());
            Message messages[] = inbox.search(new FlagTerm(new Flags(
                    Flags.Flag.SEEN), false));
            
            Folder processed = store.getFolder("PROCESSED");
            processed.open(Folder.READ_WRITE);
            
            inbox.copyMessages(messages, processed);
            
            for (Message msg : messages) {
            	 Address[] in = msg.getFrom();
                 for (Address address : in) {
                     System.out.println("FROM:" + address.toString());
                 }
                 Object content = msg.getContent();
                 if (content instanceof String) {
                	 System.out.println("CONTENT:" + content); 
                	 PrinterService.print((String)content, "CutePDF");
                 } else {
                	 Multipart mp = (Multipart) content;
                     int count = mp.getCount();
                     for (int i = 0; i < count; i++) {
                         System.out.println(mp.getBodyPart(i));
                     }
                     
                     System.out.println("SENT DATE:" + msg.getSentDate());
                 }
                 
                 System.out.println("SUBJECT:" + msg.getSubject());
                 if (msg.getSubject().trim().startsWith("help")) {
                	 msg.setFlag(Flags.Flag.DELETED, true);
                 }
			}
           
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }
}
