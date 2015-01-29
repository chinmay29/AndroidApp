package com.finalyear.cvss;



import java.util.ArrayList;
import java.util.List;

import com.finalyear.cvss.ParseNdefRecord;

/*import se.anyro.nfc_reader.record.SmartPoster;
import se.anyro.nfc_reader.record.TextRecord;
import se.anyro.nfc_reader.record.UriRecord;
*/
import android.app.Activity;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Utility class for creating {@link ParsedNdefMessage}s.
 */
public class NdefMessageParser {

    // Utility class
    private NdefMessageParser() {

    }

    /** Parse an NdefMessage */
    public static List<ParseNdefRecord> parse(NdefMessage message) {
        return getRecords(message.getRecords());
    }

    public static List<ParseNdefRecord> getRecords(NdefRecord[] records) {
        List<ParseNdefRecord> elements = new ArrayList<ParseNdefRecord>();
        for (final NdefRecord record : records) {
        /*    if (UriRecord.isUri(record)) {
                elements.add(UriRecord.parse(record));
            } else if (TextRecord.isText(record)) {
                elements.add(TextRecord.parse(record));
            } else if (SmartPoster.isPoster(record)) {
                elements.add(SmartPoster.parse(record));
            }*/
        	 
            	elements.add(new ParseNdefRecord() {
					@Override
					public View getView(Activity activity, LayoutInflater inflater, ViewGroup parent, int offset) {
				        TextView text = (TextView) inflater.inflate(R.layout.tag_text, parent, false);
				        //String a=new String(record.getPayload());
				       // Toast.makeText(NdefMessageParser.this, "Sample Text", Toast.LENGTH_LONG).show();
				        text.setText(new String(record.getPayload()));
				        return text;
				        
					}
            		
            	});
            }
        
        return elements;
    }
}