package model;

public class ArticleDTO {
  public SourceDTO source;  
  public String author;
  public String title;
  public String description;
  public String url; 
  public String publishedAt;
  public String urlToImage;
  public String content;
}

class SourceDTO{
    public String id;
    public String name;
}
