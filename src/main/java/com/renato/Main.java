package com.renato;

import com.renato.printer.PrinterStatus;
import com.renato.printer.WinspoolUpdate;
import com.renato.printer.WinspoolUtil;
import com.sun.jna.platform.win32.Winspool;


public class Main {
    public static void main(String[] args) {


        for(WinspoolUpdate.WinspoolLib.PRINTER_INFO_2 printerInfo : WinspoolUtil.getPrinterInfo2()) {

            System.out.println(printerInfo.pPrinterName + ": " + printerInfo.cJobs+" - "+printerInfo.Status+" - "+printerInfo.pLocation+" - "+ PrinterStatus.fromCode(printerInfo.Status));

        }

        WinspoolUpdate.WinspoolLib.PRINTER_INFO_2 printerInfo = WinspoolUtil.getPrinterInfo2("TESTE");
        System.out.println(printerInfo.pPrinterName + ": " + printerInfo.pDriverName + " - "+printerInfo.Status+" - "+ PrinterStatus.fromCode(printerInfo.Status));

        WinspoolUtil.print("Microsoft Print to PDF");

    }
}