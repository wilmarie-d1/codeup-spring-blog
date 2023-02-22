
package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.Ad;
import com.codeup.codeupspringblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Ad findByTitle(String title); // select * from ads where title = ?
    Ad findFirstByTitle(String title); // select * from ads where title = ? limit 1

}