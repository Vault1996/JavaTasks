package by.epam.library.parser;

import by.epam.library.entity.Book;
import by.epam.library.entity.Newsletter;
import by.epam.library.entity.Newspaper;
import by.epam.library.entity.Publication;
import by.epam.library.exception.CastException;
import by.epam.library.exception.NoSuchNameException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileParser {
    private static final Logger LOGGER = LogManager.getLogger(FileParser.class.getName());
    public ArrayList<Publication> parse(String file) {
        ArrayList<Publication> publications = new ArrayList<>();
        try {
            LOGGER.info("Start parse file: " + file);
            BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file))));
            String inputString;
            while ((inputString = buff.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(inputString, ";:-");
                String type = st.nextToken().trim();
                switch (type) {
                    case "book":
                        Book book = new Book();
                        while (st.hasMoreTokens()) {
                            String field = st.nextToken().trim();
                            switch (field) {
                                case "pages":
                                    if (st.hasMoreTokens()) {
                                        book.setPages(Integer.parseInt(st.nextToken().trim()));
                                    } else {
                                        throw new CastException();
                                    }
                                    break;
                                case "year":
                                    if (st.hasMoreTokens()) {
                                        book.setYear(Integer.parseInt(st.nextToken().trim()));
                                    } else {
                                        throw new CastException();
                                    }
                                    break;
                                case "title":
                                    if (st.hasMoreTokens()) {
                                        book.setTitle(st.nextToken().trim());
                                    } else {
                                        throw new CastException();
                                    }
                                    break;
                                case "publisher":
                                    if (st.hasMoreTokens()) {
                                        book.setPublisher(st.nextToken().trim());
                                    } else {
                                        throw new CastException();
                                    }
                                    break;
                                case "author":
                                    if (st.hasMoreTokens()) {
                                        book.setAuthor(st.nextToken().trim());
                                    } else {
                                        throw new CastException();
                                    }
                                    break;
                                case "part":
                                    if (st.hasMoreTokens()) {
                                        book.setPart(Integer.parseInt(st.nextToken().trim()));
                                    } else {
                                        throw new CastException();
                                    }
                                    break;
                                case "genre":
                                    if (st.hasMoreTokens()) {
                                        book.setGenre(st.nextToken().trim());
                                    } else {
                                        throw new CastException();
                                    }
                                    break;
                                default:
                                    throw new NoSuchNameException(field);
                            }
                        }
                        publications.add(book);
                        break;
                    case "newspaper":
                        Newspaper newspaper = new Newspaper();
                        while (st.hasMoreTokens()) {
                            String field = st.nextToken().trim();
                            switch (field) {
                                case "pages":
                                    if (st.hasMoreTokens()) {
                                        newspaper.setPages(Integer.parseInt(st.nextToken().trim()));
                                    } else {
                                        throw new CastException();
                                    }
                                    break;
                                case "year":
                                    if (st.hasMoreTokens()) {
                                        newspaper.setYear(Integer.parseInt(st.nextToken().trim()));
                                    } else {
                                        throw new CastException();
                                    }
                                    break;
                                case "title":
                                    if (st.hasMoreTokens()) {
                                        newspaper.setTitle(st.nextToken().trim());
                                    } else {
                                        throw new CastException();
                                    }
                                    break;
                                case "publisher":
                                    if (st.hasMoreTokens()) {
                                        newspaper.setPublisher(st.nextToken().trim());
                                    } else {
                                        throw new CastException();
                                    }
                                    break;
                                case "periodicity":
                                    if (st.hasMoreTokens()) {
                                        newspaper.setPeriodicity(Integer.parseInt(st.nextToken().trim()));
                                    } else {
                                        throw new CastException();
                                    }
                                    break;
                                default:
                                    throw new NoSuchNameException(field);
                            }
                        }
                        publications.add(newspaper);
                        break;
                    case "newsletter":
                        Newsletter newsletter = new Newsletter();
                        while (st.hasMoreTokens()) {
                            String field = st.nextToken().trim();
                            switch (field) {
                                case "pages":
                                    if (st.hasMoreTokens()) {
                                        newsletter.setPages(Integer.parseInt(st.nextToken().trim()));
                                    } else {
                                        throw new CastException();
                                    }
                                    break;
                                case "year":
                                    if (st.hasMoreTokens()) {
                                        newsletter.setYear(Integer.parseInt(st.nextToken().trim()));
                                    } else {
                                        throw new CastException();
                                    }
                                    break;
                                case "title":
                                    if (st.hasMoreTokens()) {
                                        newsletter.setTitle(st.nextToken().trim());
                                    } else {
                                        throw new CastException();
                                    }
                                    break;
                                case "publisher":
                                    if (st.hasMoreTokens()) {
                                        newsletter.setPublisher(st.nextToken().trim());
                                    } else {
                                        throw new CastException();
                                    }
                                    break;
                                case "purpose":
                                    if (st.hasMoreTokens()) {
                                        newsletter.setPurpose(st.nextToken().trim());
                                    } else {
                                        throw new CastException();
                                    }
                                    break;
                                default:
                                    throw new NoSuchNameException(field);
                            }
                        }
                        publications.add(newsletter);
                        break;
                }
            }
            buff.close();
        } catch (IOException e) {
            LOGGER.error("IO Exception");
        } catch (NoSuchNameException e) {
            LOGGER.error("Can't find such field.");
        } catch (CastException e) {
            LOGGER.error("Can't find argument.");
        }
        return publications;
    }

}
