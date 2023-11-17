package com.renato.printer;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;
import com.sun.jna.platform.win32.Winspool.PRINTER_INFO_1;
import com.sun.jna.platform.win32.Winspool.PRINTER_INFO_4;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.ptr.IntByReference;
public class WinspoolUtil {
    public static PRINTER_INFO_1[] getPrinterInfo1() {
        IntByReference pcbNeeded = new IntByReference();
        IntByReference pcReturned = new IntByReference();
        WinspoolUpdate.WinspoolLib.INSTANCE.EnumPrinters(WinspoolUpdate.WinspoolLib.PRINTER_ENUM_LOCAL,
                null, 1, null, 0, pcbNeeded, pcReturned);
        if (pcbNeeded.getValue() <= 0) {
            return new PRINTER_INFO_1[0];
        }

        PRINTER_INFO_1 pPrinterEnum = new PRINTER_INFO_1(pcbNeeded.getValue());
        if (!WinspoolUpdate.WinspoolLib.INSTANCE.EnumPrinters(WinspoolUpdate.WinspoolLib.PRINTER_ENUM_LOCAL,
                null, 1, pPrinterEnum.getPointer(), pcbNeeded.getValue(), pcbNeeded, pcReturned)) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }

        pPrinterEnum.read();

        return (PRINTER_INFO_1[]) pPrinterEnum.toArray(pcReturned.getValue());
    }

    public static WinspoolUpdate.WinspoolLib.PRINTER_INFO_2[] getPrinterInfo2() {
        IntByReference pcbNeeded = new IntByReference();
        IntByReference pcReturned = new IntByReference();
        WinspoolUpdate.WinspoolLib.INSTANCE.EnumPrinters(WinspoolUpdate.WinspoolLib.PRINTER_ENUM_LOCAL,
                null, 2, null, 0, pcbNeeded, pcReturned);
        if (pcbNeeded.getValue() <= 0) {
            return new WinspoolUpdate.WinspoolLib.PRINTER_INFO_2[0];
        }

        WinspoolUpdate.WinspoolLib.PRINTER_INFO_2 pPrinterEnum = new WinspoolUpdate.WinspoolLib.PRINTER_INFO_2(pcbNeeded.getValue());
        if (!WinspoolUpdate.WinspoolLib.INSTANCE.EnumPrinters(WinspoolUpdate.WinspoolLib.PRINTER_ENUM_LOCAL,
                null, 2, pPrinterEnum.getPointer(), pcbNeeded.getValue(), pcbNeeded, pcReturned)) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }

        pPrinterEnum.read();

        return (WinspoolUpdate.WinspoolLib.PRINTER_INFO_2[]) pPrinterEnum.toArray(pcReturned.getValue());
    }


    public static PRINTER_INFO_4[] getPrinterInfo4() {
        IntByReference pcbNeeded = new IntByReference();
        IntByReference pcReturned = new IntByReference();
        WinspoolUpdate.WinspoolLib.INSTANCE.EnumPrinters(WinspoolUpdate.WinspoolLib.PRINTER_ENUM_LOCAL,
                null, 4, null, 0, pcbNeeded, pcReturned);
        if (pcbNeeded.getValue() <= 0) {
            return new PRINTER_INFO_4[0];
        }

        PRINTER_INFO_4 pPrinterEnum = new PRINTER_INFO_4(pcbNeeded.getValue());
        if (!WinspoolUpdate.WinspoolLib.INSTANCE.EnumPrinters(WinspoolUpdate.WinspoolLib.PRINTER_ENUM_LOCAL,
                null, 4, pPrinterEnum.getPointer(), pcbNeeded.getValue(), pcbNeeded, pcReturned)) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }

        pPrinterEnum.read();

        return (PRINTER_INFO_4[]) pPrinterEnum.toArray(pcReturned.getValue());
    }

    public static WinspoolUpdate.WinspoolLib.PRINTER_INFO_2 getPrinterInfo2(String printerName) {
        IntByReference pcbNeeded = new IntByReference();
        IntByReference pcReturned = new IntByReference();
        HANDLEByReference pHandle = new HANDLEByReference();

        WinspoolUpdate.WinspoolLib.INSTANCE.OpenPrinter(printerName, pHandle, null);

        WinspoolUpdate.WinspoolLib.INSTANCE.GetPrinter(pHandle.getValue(), 2, null, 0, pcbNeeded);
        if (pcbNeeded.getValue() <= 0) {
            return new WinspoolUpdate.WinspoolLib.PRINTER_INFO_2();
        }

        WinspoolUpdate.WinspoolLib.PRINTER_INFO_2 pinfo2 = new WinspoolUpdate.WinspoolLib.PRINTER_INFO_2(pcbNeeded.getValue());

        WinspoolUpdate.WinspoolLib.INSTANCE.GetPrinter(pHandle.getValue(), 2, pinfo2.getPointer(), pcbNeeded.getValue(), pcReturned);

        pinfo2.read();
        return (WinspoolUpdate.WinspoolLib.PRINTER_INFO_2) pinfo2;
    }


    public static void print(String printerName){
        // Abrindo a impressora
        HANDLEByReference hPrinter = new HANDLEByReference();
        WinDef.DWORDByReference pcbWritten = new WinDef.DWORDByReference();

        if (WinspoolUpdate.WinspoolLib.INSTANCE.OpenPrinter(printerName, hPrinter, null)) {
            WinspoolUpdate.WinspoolLib.DOC_INFO_1 docInfo = new WinspoolUpdate.WinspoolLib.DOC_INFO_1();
            docInfo.pDocName = "Meu Documento"; // Nome do documento
            docInfo.pOutputFile = null;
            docInfo.pDataType = "RAW";

            // Iniciando um documento
            if (WinspoolUpdate.WinspoolLib.INSTANCE.StartDocPrinter(hPrinter.getValue(), 1, docInfo)) {
                if (WinspoolUpdate.WinspoolLib.INSTANCE.StartPagePrinter(hPrinter.getValue())) {
                    String textoParaImprimir = "Olá, mundo!"; // Texto a ser impresso
                    byte[] data = textoParaImprimir.getBytes(); // Convertendo o texto em bytes

                    // Escrevendo para a impressora
                    if (WinspoolUpdate.WinspoolLib.INSTANCE.WritePrinter(hPrinter.getValue(), data, new DWORD(data.length), pcbWritten)) {
                        // Finalizando a página
                        WinspoolUpdate.WinspoolLib.INSTANCE.EndPagePrinter(hPrinter.getValue());
                    }

                    // Finalizando o documento
                    WinspoolUpdate.WinspoolLib.INSTANCE.EndDocPrinter(hPrinter.getValue());
                }
            }

            // Fechando a impressora
            WinspoolUpdate.WinspoolLib.INSTANCE.ClosePrinter(hPrinter.getValue());
        } else {
            System.out.println("Não foi possível abrir a impressora: " + printerName);
        }
    }

}