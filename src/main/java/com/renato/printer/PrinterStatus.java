package com.renato.printer;

public enum PrinterStatus {
    PRINTER_STATUS_READY(0, "PRINTER_STATUS_READY", "A impressora está pronta para receber trabalhos de impressão."),
    PRINTER_STATUS_PAUSED(1, "PRINTER_STATUS_PAUSED", "A impressora está pausada."),
    PRINTER_STATUS_ERROR(2, "PRINTER_STATUS_ERROR", "A impressora está em um estado de erro."),
    PRINTER_STATUS_PENDING_DELETION(3, "PRINTER_STATUS_PENDING_DELETION", "A impressora está aguardando para ser excluída."),
    PRINTER_STATUS_PAPER_JAM(4, "PRINTER_STATUS_PAPER_JAM", "Há um atolamento de papel na impressora."),
    PRINTER_STATUS_PAPER_OUT(5, "PRINTER_STATUS_PAPER_OUT", "A impressora está sem papel."),
    PRINTER_STATUS_MANUAL_FEED(6, "PRINTER_STATUS_MANUAL_FEED", "A impressora está aguardando a alimentação manual de papel."),
    PRINTER_STATUS_PAPER_PROBLEM(7, "PRINTER_STATUS_PAPER_PROBLEM", "Existe um problema relacionado ao papel na impressora."),
    PRINTER_STATUS_OFFLINE(8, "PRINTER_STATUS_OFFLINE", "A impressora está offline."),
    PRINTER_STATUS_IO_ACTIVE(9, "PRINTER_STATUS_IO_ACTIVE", "Há uma operação de E/S (entrada/saída) ativa."),
    PRINTER_STATUS_BUSY(10, "PRINTER_STATUS_BUSY", "A impressora está ocupada."),
    PRINTER_STATUS_PRINTING(11, "PRINTER_STATUS_PRINTING", "A impressora está imprimindo."),
    PRINTER_STATUS_OUTPUT_BIN_FULL(12, "PRINTER_STATUS_OUTPUT_BIN_FULL", "A bandeja de saída está cheia."),
    PRINTER_STATUS_NOT_AVAILABLE(13, "PRINTER_STATUS_NOT_AVAILABLE", "A impressora não está disponível."),
    PRINTER_STATUS_WAITING(14, "PRINTER_STATUS_WAITING", "A impressora está aguardando."),
    PRINTER_STATUS_PROCESSING(15, "PRINTER_STATUS_PROCESSING", "A impressora está processando um trabalho."),
    PRINTER_STATUS_INITIALIZING(16, "PRINTER_STATUS_INITIALIZING", "A impressora está inicializando."),
    PRINTER_STATUS_WARMING_UP(17, "PRINTER_STATUS_WARMING_UP", "A impressora está aquecendo."),
    PRINTER_STATUS_TONER_LOW(18, "PRINTER_STATUS_TONER_LOW", "O toner da impressora está baixo."),
    PRINTER_STATUS_NO_TONER(19, "PRINTER_STATUS_NO_TONER", "A impressora está sem toner."),
    PRINTER_STATUS_PAGE_PUNT(20, "PRINTER_STATUS_PAGE_PUNT", "A impressora não pode imprimir a página atual."),
    PRINTER_STATUS_USER_INTERVENTION(21, "PRINTER_STATUS_USER_INTERVENTION", "A impressora requer intervenção do usuário."),
    PRINTER_STATUS_OUT_OF_MEMORY(22, "PRINTER_STATUS_OUT_OF_MEMORY", "A impressora está sem memória suficiente para continuar a imprimir."),
    PRINTER_STATUS_DOOR_OPEN(23, "PRINTER_STATUS_DOOR_OPEN", "A porta da impressora está aberta."),
    PRINTER_STATUS_SERVER_UNKNOWN(24, "PRINTER_STATUS_SERVER_UNKNOWN", "O status do servidor é desconhecido."),
    PRINTER_STATUS_POWER_SAVE(25, "PRINTER_STATUS_POWER_SAVE", "A impressora está no modo de economia de energia.");

    private final int code;
    private final String status;
    private final String description;

    PrinterStatus(int code, String status, String description) {
        this.code = code;
        this.status = status;
        this.description = description;
    }

    public int getCode() {
        return this.code;
    }

    public String getStatus() {
        return this.status;
    }

    public String getDescription() {
        return this.description;
    }

    public static PrinterStatus fromCode(int code) {
        for (PrinterStatus ps : values()) {
            if (ps.getCode() == code) {
                return ps;
            }
        }
        return null; // Ou maneje de outra forma caso o status não seja encontrado
    }
}
