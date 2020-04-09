<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <table  class="table table-hover table-bordered table-responsive">
  <thead>
    <tr>
     <th scope="col">球员ID</th>
            <th scope="col">球员英文名</th>
            <th scope="col">球员中文名</th>
            <th scope="col">进球数</th>
            <th scope="col">助攻数</th>
            <th scope="col">拦截数</th>
            <th scope="col">球衣号码</th>
            <th scope="col">效力球队</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${dimList}" var="play">
          <tr>
            <td>${play.player_id}</td>
            <td>${play.player_Ename}</td>
            <td>${play.player_Cname}</td>
            <td>${play.player_goal}</td>
            <td>${play.player_assists}</td>
            <td>${play.player_interceptions}</td>
            <td>${play.player_number}</td>
            <td>${play.player_effectiveTeam}</td>
          </tr>
          </c:forEach>
  </tbody>
</table>