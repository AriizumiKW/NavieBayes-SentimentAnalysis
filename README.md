# NavieBayes-SentimentAnalysis
A Navie Navie-Bayes    
一个很朴素的朴素贝叶斯用户情绪分析器

The training set and testing set is from：   
[——University of Michigan Sentiment Analysis competition on Kaggle](https://www.kaggle.com/)   
[——Twitter Sentiment Corpus by Niek Sanders](http://www.sananalytics.com/lab/twitter-sentiment/)    

This code written for fun. Only with 75% success rate. Cheers!   
不是作业，我自己写着玩的。成功率75%左右，你们随意看看。

How to use the data set?   
1. The data set contains 1,578,627 classified tweets. I choose 100,000 of them to be testing set, and the rest to be traing set.
2. Each row is tagged as 1 or 0. 1 for positive sentiment and 0 for negative sentiment.
   
      
Something you should know:   
Those data is coming from Twitter. But, twitter is more likely to use punctuation (!,?,...) to express and usually it not conform to  grammatical rules. To make the Bayesian Model more precisely, you may consider the sentence structure and some other features.

Data set download link given:   
[Twitter Sentiment Analysis Training Corpus (Dataset)](http://thinknook.com/twitter-sentiment-analysis-training-corpus-dataset-2012-09-22/)
