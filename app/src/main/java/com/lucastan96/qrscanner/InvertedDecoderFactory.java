package com.lucastan96.qrscanner;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.common.HybridBinarizer;
import com.journeyapps.barcodescanner.Decoder;
import com.journeyapps.barcodescanner.DecoderFactory;

import java.util.Map;

public class InvertedDecoderFactory implements DecoderFactory {
    @Override
    public Decoder createDecoder(Map<DecodeHintType, ?> baseHints) {
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        multiFormatReader.setHints(baseHints);
        return new InvertedDecoder(multiFormatReader);
    }

    static class InvertedDecoder extends Decoder {
        InvertedDecoder(Reader reader) {
            super(reader);
        }

        @Override
        protected BinaryBitmap toBitmap(LuminanceSource source) {
            return new BinaryBitmap(new HybridBinarizer(source.invert()));
        }
    }
}
