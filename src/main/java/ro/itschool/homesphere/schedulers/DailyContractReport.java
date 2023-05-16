package ro.itschool.homesphere.schedulers;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.itschool.homesphere.repositories.ContractRepository;
import ro.itschool.homesphere.repositories.ContractsCount;
import ro.itschool.homesphere.service.EmailService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@Component
@Slf4j
public class DailyContractReport {

    public static final String EMAIL = "maximmihalcea@gmail.com";
    @Autowired
    private EmailService emailService;

    @Autowired
    private ContractRepository contractRepository;

    @Scheduled(cron = "0 */1 * ? * *")
    public void sendReport() {

        log.trace("Fetching all contracts...");
        List<ContractsCount> contractList = contractRepository.countContracts();
        log.trace("Contracts fetched successfully.");

        try (StringWriter stringWriter = new StringWriter()) {

            StatefulBeanToCsv<ContractsCount> beanToCsv = new StatefulBeanToCsvBuilder(stringWriter)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .build();

            beanToCsv.write(contractList);

            String csvReport = stringWriter.toString();

            this.emailService.sendEmail(EMAIL, "Weekly report", "Hello! This is your report.", csvReport);
            log.debug("Email sent successfully!");

        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            log.error("Something exception happened when creating report {}", e.getMessage());


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        }
    }
