FROM nginx
RUN rm /etc/nginx/conf.d/default.conf
COPY default.conf /etc/nginx/conf.d/default.conf
RUN chmod -R 777 /etc/nginx
VOLUME /etc/nginx/conf.d/