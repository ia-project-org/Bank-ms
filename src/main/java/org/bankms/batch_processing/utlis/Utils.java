package org.bankms.batch_processing.utlis;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

public class Utils {

    /**
     * generic methode to get the data from the csv file
     */
    public static <T> FlatFileItemReader<T> createItemReader(String filePath,String readerName) {
        FlatFileItemReader<T> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource(filePath));
        itemReader.setName(readerName);
        itemReader.setLinesToSkip(1);
        return itemReader;
    }

    /**
     * generic methode to get the data from the db
     */
    public static  <T> JpaPagingItemReader<T> createJpaPagingItemReader(EntityManagerFactory entityManagerFactory, String query, String readerName) {
        JpaPagingItemReader<T> reader = new JpaPagingItemReader<>();
        reader.setQueryString(query);
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setPageSize(10); // Adjust as needed
        reader.setName(readerName);
        return reader;
    }

    /**
     * generic methode to store the data from the csv file
     */
    public static <T> FlatFileItemWriter<T> createFlatFileItemWriter(String filePath, String[] headerNames, String[] fieldNames) {
        FlatFileItemWriter<T> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource(filePath));
        writer.setHeaderCallback(writer1 -> writer1.write(String.join(",", headerNames)));
        writer.setLineAggregator(new DelimitedLineAggregator<>() {{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<>() {{
                setNames(fieldNames);
            }});
        }});
        return writer;
    }


}
