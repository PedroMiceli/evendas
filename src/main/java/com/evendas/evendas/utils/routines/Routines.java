package com.evendas.evendas.utils.routines;

import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Routines {

    public static String percentFormatterFloatToString(float numero){
        try {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            return decimalFormat.format(numero) + "%";
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static float percentFormatterStringToFloat(String percentage){
        try {
            // Remove o símbolo de porcentagem
            String numericPart = percentage.replace("%", "").trim();

            // Substitui a vírgula por ponto para garantir o formato correto para conversão
            numericPart = numericPart.replace(",", ".");

            // Converte a string para float
            return Float.parseFloat(numericPart);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static LocalDate localDateFormatter(String dateString) {
        try {
            if (dateString == null || dateString.isEmpty() || dateString.isBlank()) {
                return null;
            }

            // Crie um DateTimeFormatter para o padrão dd/MM/yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Faça a conversão para LocalDate
            LocalDate date = LocalDate.parse(dateString, formatter);

            return date;
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Year yearFormatter(String year){
        try {

            if (year == null || year.isBlank() || year.isEmpty())
                return null;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
            return Year.parse(year, formatter);
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static YearMonth yearMonthFormatter(String yearMonth){
        try {


            if (yearMonth == null || yearMonth.isBlank() || yearMonth.isEmpty())
                return null;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
            YearMonth yearMonthObj = YearMonth.parse(yearMonth, formatter);
            return yearMonthObj;

        }catch (DateTimeParseException ex) {
            throw new DateTimeParseException("Período Inválido", ex.getParsedString(), ex.getErrorIndex());
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static String yearMonthStringFormatter(YearMonth yearMonth){
        if (yearMonth == null)
            return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        String formattedDate = yearMonth.format(formatter);
        return formattedDate;
    }

    public static LocalTime localTimeFormatter(String timeString) {
        try {
            if (timeString == null || timeString.isEmpty() || timeString.isBlank()) {
                return null;
            }

            // Crie um DateTimeFormatter para o padrão dd/MM/yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            // Faça a conversão para LocalDate
            LocalTime time = LocalTime.parse(timeString, formatter);

            return time;
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String removeMaskCPF(String cpf) {
        return cpf.replace(".", "").replace("-", "");
    }

    public static String dateStringFormatter(LocalDate date) {
        try {
            if (date == null)
                return null;

            return String.format("%1$td/%1$tm/%1$tY", date);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String dateStringFormatterWithoutYear(LocalDate date) {
        try {
            if (date == null)
                return null;

            return String.format("%1$td/%1$tm", date);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String dateTimeStringFormatter(LocalDateTime date) {
        try {
            if (date == null)
                return null;

            return String.format("%1$td/%1$tm/%1$tY %1$tH:%1$tM:%1$tS", date);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String dateStringFormatter(LocalDateTime date) {
        try {
            if (date == null)
                return null;

            return String.format("%1$td/%1$tm/%1$tY", date);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String timeStringFormatter(LocalTime time) {
        try {
            if (time == null)
                return null;

            return String.format("%02d:%02d", time.getHour(), time.getMinute());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String floatStringFormatter(float value) {
        try {
            if (value == 0)
                return "R$ 0,00";

            Locale brasil = new Locale("pt", "BR");
            String format = "%,.2f"; // Define o formato como número com 2 casas decimais separadas por vírgula
            String valueFormatted = String.format(brasil, format, value);

            return "R$ "+valueFormatted;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String floatStringFormatterNotCoin(float value) {
        try {
            if (value == 0)
                return "0,00";

            Locale brasil = new Locale("pt", "BR");
            String format = "%,.2f"; // Define o formato como número com 2 casas decimais separadas por vírgula
            String valueFormatted = String.format(brasil, format, value);

            return valueFormatted;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static float floatFormatter(String valueString) {
        try {
            if (valueString.isEmpty() || valueString.isBlank())
                return Float.parseFloat(null);

            valueString = valueString.replaceAll("\\.", "").replaceAll(",", ".").replace("R$","").trim();

            float value = Float.parseFloat(valueString);
            return roundTwoDecimals(value);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static LocalDate getFirstDayOfMonth() {
        return LocalDate.now().withDayOfMonth(1);
    }

    public static String getNextLetter(String letter) {
        try {
            String lastLetter = String.valueOf(letter.charAt(letter.length() - 1));
            int asciiLetter = (int) lastLetter.charAt(0);

            if (lastLetter.equals("Z"))
                letter += "A";
            else {
                letter = letter.substring(0, letter.length() - 1);
                letter += (char) (asciiLetter + 1);
            }

            return letter;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static boolean compareActualDate(LocalDate date) {
        try {
            if (date == null)
                return true;

            LocalDate dateNow = LocalDate.now();

            return date.isAfter(dateNow);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static long calculateIntervalInDays(LocalDate firstDate, LocalDate secondDate) {
        try {
            return ChronoUnit.DAYS.between(firstDate, secondDate) + 1; // somar + 1, por conta que a função conta um dia a menos
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static float roundTwoDecimals(float value) {
        try {
            if (value == 0)
                return 0;

            return Math.round(value * 100.0f) / 100.0f;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static float calculatePorcentagemDiferenca(float valor1, float valor2) throws Exception {
        try {
            if (valor1 == 0)
                return 0;

            float porcentagem = ((valor2 / valor1) - 1) * 100;
            return Routines.roundTwoDecimals(porcentagem);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public static float calculatePorcentagemRepresentativa(float valor1, float valor2) throws Exception {
        try {
            if (valor1 == 0)
                return 0;

            float porcentagem = (valor1 / valor2) * 100;
            return Routines.roundTwoDecimals(porcentagem);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public static String formatterTotalHoras(long totalMinutos) throws Exception {
        try {
            long horas = totalMinutos / 60;
            long minutos = totalMinutos % 60;

            String total = horas + ":";

            if (minutos <= 9)
                total += "0" + minutos;
            else
                total += minutos;

            return total;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public static Duration convertLocalTimeForDuration(LocalTime time) throws Exception {
        try {
            if (time == null)
                return null;

            return Duration.between(LocalTime.MIN, time);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public static boolean betweenLocalDates(LocalDate dataInicialAComparar, LocalDate dataFinalAComparar, LocalDate data)throws Exception{
        try {
            return (data.isAfter(dataInicialAComparar) || data.isEqual(dataInicialAComparar)) || (data.isBefore(dataFinalAComparar) || data.isEqual(dataFinalAComparar));
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
