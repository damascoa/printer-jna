package com.renato.printer;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.INT_PTR;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

import java.util.Arrays;
import java.util.List;
public class WinspoolUpdate {
    public interface WinspoolLib extends StdCallLibrary {

        WinspoolLib INSTANCE = (WinspoolLib) Native.loadLibrary("Winspool.drv", WinspoolLib.class,
                W32APIOptions.UNICODE_OPTIONS);

        boolean EnumPrinters(int Flags, String Name, int Level, Pointer pPrinterEnum,
                             int cbBuf, IntByReference pcbNeeded, IntByReference pcReturned);

        boolean GetPrinter(HANDLE hPrinter, int Level, Pointer pPrinter, int cbBuf, IntByReference pcbNeeded);

        boolean OpenPrinter(String pPrinterName, HANDLEByReference phPrinter, Pointer pDefault);

        public static class PRINTER_INFO_1 extends Structure {
            public int Flags;
            public String pDescription;
            public String pName;
            public String pComment;

            protected List<String> getFieldOrder() {
                return Arrays.asList(new String[] { "Flags", "pDescription", "pName", "pComment" });
            }

            public PRINTER_INFO_1() {
            }

            public PRINTER_INFO_1(int size) {
                super(new Memory(size));
            }
        }

        public static class PRINTER_INFO_2 extends Structure {
            public String pServerName;
            public String pPrinterName;
            public String pShareName;
            public String pPortName;
            public String pDriverName;
            public String pComment;
            public String pLocation;
            public INT_PTR pDevMode;
            public String pSepFile;
            public String pPrintProcessor;
            public String pDatatype;
            public String pParameters;
            public INT_PTR pSecurityDescriptor;
            public int Attributes;
            public int Priority;
            public int DefaultPriority;
            public int StartTime;
            public int UntilTime;
            public int Status;
            public int cJobs;
            public int AveragePPM;

            protected List<String> getFieldOrder() {
                return Arrays.asList(new String[] { "pServerName", "pPrinterName", "pShareName", "pPortName",
                        "pDriverName", "pComment", "pLocation", "pDevMode", "pSepFile", "pPrintProcessor",
                        "pDatatype", "pParameters", "pSecurityDescriptor", "Attributes", "Priority", "DefaultPriority",
                        "StartTime", "UntilTime", "Status", "cJobs", "AveragePPM" });
            }

            public PRINTER_INFO_2() {
            }

            public PRINTER_INFO_2(int size) {
                super(new Memory(size));
            }
        }

        public static class PRINTER_INFO_4 extends Structure {
            public String pPrinterName;
            public String pServerName;
            public DWORD Attributes;

            protected List<String> getFieldOrder() {
                return Arrays.asList(new String[] { "pPrinterName", "pServerName", "Attributes" });
            }

            public PRINTER_INFO_4() {
            }

            public PRINTER_INFO_4(int size) {
                super(new Memory(size));
            }
        }

        int PRINTER_ENUM_DEFAULT = 0x00000001;
        int PRINTER_ENUM_LOCAL = 0x00000002;
        int PRINTER_ENUM_CONNECTIONS = 0x00000004;
        int PRINTER_ENUM_FAVORITE = 0x00000004;
        int PRINTER_ENUM_NAME = 0x00000008;
        int PRINTER_ENUM_REMOTE = 0x00000010;
        int PRINTER_ENUM_SHARED = 0x00000020;
        int PRINTER_ENUM_NETWORK = 0x00000040;

        int PRINTER_ENUM_EXPAND = 0x00004000;
        int PRINTER_ENUM_CONTAINER = 0x00008000;

        int PRINTER_ENUM_ICONMASK = 0x00ff0000;
        int PRINTER_ENUM_ICON1 = 0x00010000;
        int PRINTER_ENUM_ICON2 = 0x00020000;
        int PRINTER_ENUM_ICON3 = 0x00040000;
        int PRINTER_ENUM_ICON4 = 0x00080000;
        int PRINTER_ENUM_ICON5 = 0x00100000;
        int PRINTER_ENUM_ICON6 = 0x00200000;
        int PRINTER_ENUM_ICON7 = 0x00400000;
        int PRINTER_ENUM_ICON8 = 0x00800000;
        int PRINTER_ENUM_HIDE = 0x01000000;
    }
}