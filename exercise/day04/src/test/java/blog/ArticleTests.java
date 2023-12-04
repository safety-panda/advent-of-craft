package blog;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleTests {
    @Test
    void it_should_add_valid_comment_with_expected_data() throws CommentAlreadyExistException {
        var article = new Article(
                "Lorem Ipsum",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );

        assertThat(article.getComments()).hasSize(0);

        var text = "Amazing article !!!";
        var author = "Pablo Escobar";
        article.addComment(text, author);

        var date = LocalDate.now();

        assertThat(article.getComments())
                .hasSize(1)
                .anyMatch(comment -> comment.text().matches(text))
                .anyMatch(comment -> comment.author().matches(author))
                .anyMatch(comment -> comment.creationDate().equals(date));
    }
    

    @Test
    void it_should_throw_an_exception_when_adding_existing_comment() throws CommentAlreadyExistException {
        var article = new Article(
                "Lorem Ipsum",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );
        article.addComment("Amazing article !!!", "Pablo Escobar");

        assertThatThrownBy(() -> {
            article.addComment("Amazing article !!!", "Pablo Escobar");
        }).isInstanceOf(CommentAlreadyExistException.class);
    }
}
